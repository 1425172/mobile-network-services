package at.ac.tuwien.nsa.gr12.comparelocations.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Report(
    var id: Long? = null,
    var date: Date? = null,
    var gpsLocation: Location? = null,
    var accessPoints: List<AccessPoint> = mutableListOf(),
    var cellTowers: List<CellTower> = mutableListOf(),
    var mlsLocation: Location? = null
) : Parcelable {
    constructor() : this(null, null, null, mutableListOf(), mutableListOf(), null)

    fun distance(): Double {
        if (gpsLocation == null || mlsLocation == null) {
            return 0.0
        }
        if (gpsLocation!!.latitude == null || gpsLocation!!.longitude == null) {
            return 0.0
        }
        if (mlsLocation!!.latitude == null || mlsLocation!!.longitude == null) {
            return 0.0
        }

        val gpsAndroidLocation = android.location.Location("")
        gpsAndroidLocation.latitude = gpsLocation!!.latitude!!
        gpsAndroidLocation.longitude = gpsLocation!!.longitude!!

        val mlsAndroidLocation = android.location.Location("")
        mlsAndroidLocation.latitude = mlsLocation!!.latitude!!
        mlsAndroidLocation.longitude = mlsLocation!!.longitude!!

        return gpsAndroidLocation.distanceTo(mlsAndroidLocation).toDouble()
    }

    override fun toString(): String {
        return "Report(id=$id, date=$date, gpsLocation=$gpsLocation, accessPoints=$accessPoints, cellTowers=$cellTowers, mlsLocation=$mlsLocation)"
    }

}
