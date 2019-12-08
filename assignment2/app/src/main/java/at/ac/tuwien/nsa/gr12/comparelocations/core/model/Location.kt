package at.ac.tuwien.nsa.gr12.comparelocations.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    var latitude: Double? = null,
    var longitude: Double? = null,
    var accuracy: Float? = null
):Parcelable {
    constructor() : this(null, null, null)

    override fun toString(): String {
        return "Location(latitude=$latitude, longitude=$longitude, accuracy=$accuracy)"
    }


}
