package at.ac.tuwien.nsa.gr12.comparelocations.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import at.ac.tuwien.nsa.gr12.comparelocations.R
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report
import at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases.MailUseCase
import at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases.ReportUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class DetailsFragment(val report: Report) : Fragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private var viewModel: ReportListViewModel? = null

    companion object {
        fun newInstance(
            report: Report
        ) = DetailsFragment(report)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragment, container, false)
        val buttonMail = view.findViewById<Button>(R.id.buttonMail)
        buttonMail.setOnClickListener {
            GlobalScope.launch {
                val intent = viewModel?.send(report)
                startActivity(intent)
            }

            }
        val buttonDelete = view.findViewById<Button>(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            viewModel!!.remove(report)
            activity?.supportFragmentManager?.popBackStack()
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {  ViewModelProviders.of(this, ReportListViewModelFactory(context!!)).get(ReportListViewModel::class.java)}
    }


}
