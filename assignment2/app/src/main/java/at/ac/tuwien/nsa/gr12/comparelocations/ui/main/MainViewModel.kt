package at.ac.tuwien.nsa.gr12.comparelocations.ui.main

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.model.MLSCellTower
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report
import at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases.ReportUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MainViewModel(app: Context) : KodeinAware, ViewModel() {

    override val kodein: Kodein by closestKodein(app)

    private val reportUseCase by instance<ReportUseCase>()

    private lateinit var allReports: MutableLiveData<List<Report>>

    init {
        GlobalScope.launch {
            allReports = MutableLiveData(reportUseCase.getAll()as MutableList<Report>)
        }
    }
    init {
        Log.i("Created Model","Created!")
    }



    fun getAllData(): LiveData<List<Report>> {
        return allReports
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
    suspend fun getNew(){
        allReports.postValue((allReports.value as MutableList<Report>).also {it.add(reportUseCase.getNew())})
    }

    /**
     * NOT TESTED
     * Should remove the specified report
     */
    fun remove(report: Report){
        (allReports.value as MutableList<Report>).remove(report)
        GlobalScope.launch {
            reportUseCase.remove(report)
        }
    }

}
