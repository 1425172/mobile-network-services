package at.ac.tuwien.nsa.gr12.comparelocations.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report
import at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases.ReportUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    lateinit var reportUseCase: ReportUseCase

    private lateinit var allReports: LiveData<List<Report>>

    fun initialize() {
        GlobalScope.launch {
            allReports = MutableLiveData<List<Report>>(reportUseCase.getAll())
        }
    }

    // TODO: Implement the ViewModel
    init {
        Log.i("Created Model", "Created!")
    }

    fun getAllData(): LiveData<List<Report>> {
        return allReports
    }

    suspend fun getNew() {
        reportUseCase.getNew()
    }

    suspend fun remove(report: Report) {
        reportUseCase.remove(report)
    }

}
