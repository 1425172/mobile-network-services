package at.ac.tuwien.nsa.gr12.comparelocations.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ReportListViewModelFactory(var app: Context) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReportListViewModel(app) as T
    }

}
