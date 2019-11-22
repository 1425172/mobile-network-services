package at.ac.tuwien.nsa.gr12.comparelocations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import at.ac.tuwien.nsa.gr12.comparelocations.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

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
