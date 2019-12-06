package at.ac.tuwien.nsa.gr12.comparelocations.ui.main

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import at.ac.tuwien.nsa.gr12.comparelocations.R
import at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases.MailUseCase
import at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases.ReportUseCase
import at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases.SecurityUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MainFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val reportUseCase by instance<ReportUseCase>()
    private val mailUseCase by instance<MailUseCase>()
    private val securityUseCase by instance<SecurityUseCase>()

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false);

        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    view.context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    view.context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                GlobalScope.launch {
                    securityUseCase.encryptDatabase()
                    val report = reportUseCase.getNew()
                    Log.i("##################### Report", report.toString())
                    Log.i("##################### distance", report.distance().toString())

//                    val intent = mailUseCase.send(report)
//                    startActivity(intent)

//                    reportUseCase.remove(report)

                    val reports = reportUseCase.getAll()
                    reports.forEach {
                        Log.i("################ Reports", it.toString())
                    }
                }

            } else {
                requestLocationPermission(this.activity)
            }
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.reportUseCase = reportUseCase
        viewModel.initialize()
        // TODO: Use the ViewModel
    }

    private fun requestLocationPermission(activity: Activity?) {
        ActivityCompat.requestPermissions(
            activity!!,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            12
        )
    }
}
