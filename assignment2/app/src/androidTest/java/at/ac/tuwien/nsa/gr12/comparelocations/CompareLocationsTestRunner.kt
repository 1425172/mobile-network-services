package at.ac.tuwien.nsa.gr12.comparelocations

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class CompareLocationsTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }
}