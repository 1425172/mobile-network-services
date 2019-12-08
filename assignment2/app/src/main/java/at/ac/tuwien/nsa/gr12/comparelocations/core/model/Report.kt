package at.ac.tuwien.nsa.gr12.comparelocations.core.model

import java.util.*

data class Report(
    var id: Long? = null,
    var date: Date? = null,
    var gpsLocation: Location? = null,
    var accessPoints: List<AccessPoint> = mutableListOf(),
    var cellTowers: List<CellTower> = mutableListOf(),
    var mlsLocation: Location? = null
) {
    constructor() : this(null, null, null, mutableListOf(), mutableListOf(), null)

    fun distance(): String {
        if (gpsLocation == null || mlsLocation == null) {
            return "0"
        }

        val gpsAndroidLocation = android.location.Location("")
        gpsAndroidLocation.latitude = gpsLocation!!.latitude!!
        gpsAndroidLocation.longitude = gpsLocation!!.longitude!!

        val mlsAndroidLocation = android.location.Location("")
        mlsAndroidLocation.latitude = mlsLocation!!.latitude!!
        mlsAndroidLocation.longitude = mlsLocation!!.longitude!!

        return gpsAndroidLocation.distanceTo(mlsAndroidLocation).toString()
    }

    override fun toString(): String {
        return "Report(id=$id, date=$date, gpsLocation=$gpsLocation, accessPoints=$accessPoints, cellTowers=$cellTowers, mlsLocation=$mlsLocation)"
    }

}
