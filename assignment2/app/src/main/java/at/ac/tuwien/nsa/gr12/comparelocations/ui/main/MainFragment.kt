package at.ac.tuwien.nsa.gr12.comparelocations.ui.main

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report
import at.ac.tuwien.nsa.gr12.comparelocations.R
import at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases.SecurityUseCase
import at.ac.tuwien.nsa.gr12.comparelocations.databinding.MainFragmentBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class MainFragment : Fragment(), KodeinAware, ReportFragment.OnListFragmentInteractionListener {
    override val kodein: Kodein by closestKodein()
    private var viewModel: ReportListViewModel? = null
    var fadeTransition: Transition = Fade()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run{ViewModelProviders.of(this, ReportListViewModelFactory(context!!)).get(ReportListViewModel::class.java)}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val view = inflater.inflate(R.layout.main_fragment, container, false)
        val binding: MainFragmentBinding = MainFragmentBinding.inflate(layoutInflater,container,false)
//        val reportList: RecyclerView = view!!.findViewById(R.id.reportList)
        viewModel?.getAllReports()?.observe(this, Observer {
            binding.reportList.adapter = ReportRecyclerViewAdapter(it,this)
        })
        binding.encryptionSwitch.isChecked = viewModel!!.isEncrypted()
        if (binding.encryptionSwitch.isChecked) {
            binding.encryptionSwitch.isClickable = false
        } else {
            binding.encryptionSwitch.isClickable = true
            binding.encryptionSwitch.setOnClickListener {
                viewModel!!.enableEncryption()
                it.isClickable=false
            }
        }

//        val button = view.findViewById<Button>(R.id.button)
        binding.button.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    binding.root.context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    binding.root.context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                GlobalScope.launch {
                    try {
                        viewModel!!.getNew()
                    }
                    catch (httpException: Exception){
                        activity?.runOnUiThread {
                            Toast.makeText(activity, "MLS Backend not reachable: "+httpException.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            } else {
                requestLocationPermission(this.activity)
            }
        }
        return binding.root
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

    override fun onListFragmentInteraction(item: Report?) {
        val explodeTransition: Transition =
            TransitionInflater.from(context)
                .inflateTransition(R.transition.explode_transition)

        val fadeTransition: Transition =
            TransitionInflater.from(context)
                .inflateTransition(R.transition.fade_transition)

        val detailsFragment: DetailsFragment = DetailsFragment.newInstance(item!!)

        detailsFragment.enterTransition = explodeTransition
        detailsFragment.exitTransition = fadeTransition

        fragmentManager
        ?.beginTransaction()
        ?.replace(R.id.container, detailsFragment)
        ?.addToBackStack("hoehoe")
        ?.commit()
    }
}
