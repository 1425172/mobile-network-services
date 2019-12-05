package at.ac.tuwien.nsa.gr12.comparelocations

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import at.ac.tuwien.nsa.gr12.comparelocations.dummy.DummyContent
import at.ac.tuwien.nsa.gr12.comparelocations.ui.main.DetailsFragment
import at.ac.tuwien.nsa.gr12.comparelocations.ui.main.MainFragment
import at.ac.tuwien.nsa.gr12.comparelocations.ui.main.ReportFragment

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }


    }
}
