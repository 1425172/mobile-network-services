package at.ac.tuwien.nsa.gr12.comparelocations.adapter.wifi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager

class WifiScanReceiver(
    val then: () -> Unit,
    val catch: () -> Unit
) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val success = intent.getBooleanExtra(
            WifiManager.EXTRA_RESULTS_UPDATED, false
        )
        if (success) {
            then()
        } else {
            catch()
        }
    }
}