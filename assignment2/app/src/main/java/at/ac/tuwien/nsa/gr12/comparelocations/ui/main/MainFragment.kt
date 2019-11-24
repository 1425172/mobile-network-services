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
import at.ac.tuwien.nsa.gr12.comparelocations.core.ports.CellTowersPort
import at.ac.tuwien.nsa.gr12.comparelocations.core.ports.GPSPort
import at.ac.tuwien.nsa.gr12.comparelocations.core.ports.MozillaLocationServicePort
import at.ac.tuwien.nsa.gr12.comparelocations.core.ports.WifiPort
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MainFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val wifiPort by instance<WifiPort>()
    private val cellPort by instance<CellTowersPort>()
    private val mlsPort by instance<MozillaLocationServicePort>()
    private val gpsPort by instance<GPSPort>()

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
                    val accessPoints = wifiPort.getAsync().await()
                    val cellTowers = cellPort.getAsync().await()
                    val location = mlsPort.get(accessPoints, cellTowers)
                    Log.i("##################### MLS", location.toString())
                }
                GlobalScope.launch {
                    val gpsLocation = gpsPort.getAsync().await()
                    Log.i("##################### GPS", gpsLocation.toString())
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
