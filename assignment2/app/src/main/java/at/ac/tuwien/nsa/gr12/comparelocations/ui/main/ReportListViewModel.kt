package at.ac.tuwien.nsa.gr12.comparelocations.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report
import at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases.ReportUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class ReportListViewModel(app: Context) : KodeinAware, ViewModel() {

    override val kodein: Kodein by closestKodein(app)

    private val reportUseCase by instance<ReportUseCase>()

    val allReports: MutableLiveData<List<Report>> = MutableLiveData(mutableListOf())

    init {
        GlobalScope.launch {
            allReports.postValue((reportUseCase.getAll() as MutableList<Report>))
        }
    }

    /**
     * Issues a request for a new report and adds it to "allReports"
     * @return not the new report! (Can be changed of course)
     *
     *
     * From postValue documentation:
     * If you called this method multiple times before a main thread executed a posted task, only the last value would be dispatched.
     * -> Notify user that issuing multiple requests is useless?
     */
    suspend fun getNew() {
        allReports.postValue((allReports.value as MutableList<Report>).also { it.add(reportUseCase.getNew()) })
    }

    fun remove(report: Report) {
        allReports.value=((allReports.value as MutableList<Report>).also {it.remove(report)})
        GlobalScope.launch {
            reportUseCase.remove(report)
        }
    }

}
