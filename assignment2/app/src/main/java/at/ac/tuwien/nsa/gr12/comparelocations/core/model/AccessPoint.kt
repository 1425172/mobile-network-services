package at.ac.tuwien.nsa.gr12.comparelocations.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AccessPoint(
    var macAddress: String? = null,
    var signalStrength: Int? = null
):Parcelable {
    constructor() : this(null, null)
}
