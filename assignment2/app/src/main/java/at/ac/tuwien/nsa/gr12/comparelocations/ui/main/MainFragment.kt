package at.ac.tuwien.nsa.gr12.comparelocations.ui.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import at.ac.tuwien.nsa.gr12.comparelocations.MainActivity
import androidx.recyclerview.widget.RecyclerView
import at.ac.tuwien.nsa.gr12.comparelocations.R
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report
import at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases.MailUseCase
import at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases.ReportUseCase
import at.ac.tuwien.nsa.gr12.comparelocations.dummy.DummyContent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MainFragment : Fragment(), KodeinAware, ReportFragment.OnListFragmentInteractionListener {

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        fragmentManager?.beginTransaction()?.replace(R.id.container, DetailsFragment.newInstance())
            ?.addToBackStack("hoehoe")
            ?.commit()
    }

    override val kodein: Kodein by closestKodein()

    private val reportUseCase by instance<ReportUseCase>()
    private val mailUseCase by instance<MailUseCase>()

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
                    //                    val report = reportUseCase.getNew()
//                    Log.i("##################### Report", report.toString())
//                    Log.i("##################### distance", report.distance().toString())
//                    mailUseCase.send(report)
//                    reportUseCase.remove(report)
                    val reports = reportUseCase.getAll()
                    val intent = mailUseCase.send(reports[0])
                    startActivity(intent)

                    reports.forEach {
                        Log.i("################ Reports", it.toString())
                    }

                    //val allData: LiveData<List<Report>> = viewModel?.getAllData()
//
                    //Log.i(allData.value?.get(0)?.date.toString(), "hasdflkjsadlfjsalfdkj")
                }

            } else {
                requestLocationPermission(this.activity)
            }
        }

        // val recyclerView = view.findViewById<RecyclerView>(R.id.reportList)
        // recyclerView?.adapter = ReportRecyclerViewAdapter(DummyContent.ITEMS, this)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, MainViewModelFactory(context!!))
            .get(MainViewModel::class.java)
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
