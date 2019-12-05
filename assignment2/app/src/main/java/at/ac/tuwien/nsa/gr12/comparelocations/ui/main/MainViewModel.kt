package at.ac.tuwien.nsa.gr12.comparelocations.ui.main

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
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
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MainViewModel(app: Context) : KodeinAware, ViewModel() {

    override val kodein: Kodein by closestKodein(app)

    private val reportUseCase by instance<ReportUseCase>()

    private lateinit var allReports: LiveData<List<Report>>

    init {
        GlobalScope.launch {
            allReports = MutableLiveData<List<Report>>(reportUseCase.getAll())
        }
    }

    // TODO: Implement the ViewModel
    init {
        Log.i("Created Model","Created!")
    }

    suspend fun getAllData(): LiveData<List<Report>> {
        allReports = MutableLiveData<List<Report>>(reportUseCase.getAll())
        return allReports
    }

    suspend fun getNew(){
        reportUseCase.getNew()
    }

    suspend fun remove(report: Report){
        reportUseCase.remove(report)
    }

}
