package at.ac.tuwien.nsa.gr12.comparelocations.adapter.gps.location.service

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.os.Looper
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Location
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.GPSInterface
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import java.lang.RuntimeException

class GPSAdapter(context: Context) : GPSInterface {

    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private var deferred: CompletableDeferred<Location>? = null

    @SuppressLint("MissingPermission")
    override fun getAsync(): Deferred<Location> {
        deferred = CompletableDeferred()

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            deferred!!.completeExceptionally(RuntimeException("GPS is not activated"))
            val retDef = deferred!!
            deferred = null
            return retDef
        }
        val gpsLocationListener = GPSLocationListener(::update)

        val looper = Looper.getMainLooper()
        locationManager.requestSingleUpdate(
            LocationManager.GPS_PROVIDER,
            gpsLocationListener,
            looper
        )

        return deferred!!
    }


    private fun update(androidLocation: android.location.Location) {
        if (deferred == null) {
            return
        }

        val latitude: Double = androidLocation.latitude
        val longitude: Double? = androidLocation.longitude
        val accuracy: Float? = androidLocation.accuracy

        val location = Location(latitude, longitude, accuracy)

        deferred!!.complete(location)
        deferred = null
    }
}