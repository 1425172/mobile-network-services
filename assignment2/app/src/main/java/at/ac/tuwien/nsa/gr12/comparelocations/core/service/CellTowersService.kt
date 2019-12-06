package at.ac.tuwien.nsa.gr12.comparelocations.core.service

import android.Manifest
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.core.content.ContextCompat
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.CellTowersCacheInterface
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.CellTowersInterface
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.fullDispString
import org.kodein.di.generic.instance

class CellTowersService : Service(), KodeinAware {

    private val TAG = this.javaClass.fullDispString()

    private val INTERVAL = 10000L

    override val kodein: Kodein by closestKodein()

    private val cellTowers: CellTowersInterface by instance()
    private val cache: CellTowersCacheInterface by instance()

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        scan()
        return START_NOT_STICKY
    }

    private fun scan() {
        val handler = Handler()
        val wifiScan = WifiScan(handler)
        handler.post(wifiScan)
    }

    inner class WifiScan(private val handler: Handler) : Runnable {
        override fun run() {
            Log.i(TAG, "starting cell tower scan")
            GlobalScope.launch {
                try {
                    update()
                } catch (e: Exception) {
                    Log.w(TAG, e)
                }
            }
            handler.postDelayed(this, INTERVAL)
        }

        private suspend fun update() {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.w(TAG, "lost permission for cell tower scan")
            }
            val cellTowers = cellTowers.getAsync().await()
            cache.set(cellTowers)
        }
    }
}
