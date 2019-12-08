package at.ac.tuwien.nsa.gr12.comparelocations.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report
import at.ac.tuwien.nsa.gr12.comparelocations.databinding.DetailsFragmentBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein


class DetailsFragment : Fragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private var viewModel: ReportListViewModel? = null
    private lateinit var report:Report
    private lateinit var accessPointRecyclerView: RecyclerView
    private lateinit var accessPointViewAdapter: RecyclerView.Adapter<*>

    private lateinit var cellTowerRecyclerView: RecyclerView
    private lateinit var cellTowerViewAdapter: RecyclerView.Adapter<*>

    companion object {
        fun newInstance(report: Report) = DetailsFragment().apply {
            arguments = Bundle().apply { putParcelable("report",report) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: DetailsFragmentBinding = DetailsFragmentBinding.inflate(layoutInflater,container,false)
        binding.report = report
        accessPointRecyclerView = binding.accessPointsList.apply{adapter=AccessPointAdapter(report.accessPoints)}

        cellTowerRecyclerView = binding.cellTowersList.apply{adapter=CellTowerAdapter(report.cellTowers)}

        binding.buttonMail.setOnClickListener {
            GlobalScope.launch {
                val intent = viewModel?.send(report)
                startActivity(intent)
            }

            }
        binding.buttonDelete.setOnClickListener {
            viewModel!!.remove(report)
            activity?.supportFragmentManager?.popBackStack()
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            report = arguments!!.getParcelable("report")!!
        }
        viewModel = activity?.run {  ViewModelProviders.of(this, ReportListViewModelFactory(context!!)).get(ReportListViewModel::class.java)}
        accessPointViewAdapter = AccessPointAdapter(report.accessPoints)
        cellTowerViewAdapter = CellTowerAdapter(report.cellTowers)

    }


}
