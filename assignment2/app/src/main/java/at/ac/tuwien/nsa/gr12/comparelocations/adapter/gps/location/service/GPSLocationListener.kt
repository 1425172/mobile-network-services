package at.ac.tuwien.nsa.gr12.comparelocations.adapter.gps.location.service

import android.location.Location
import android.location.LocationListener
import android.os.Bundle

class GPSLocationListener(val update: (Location) -> Unit) : LocationListener {
    override fun onLocationChanged(location: Location) {
        update(location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }
}