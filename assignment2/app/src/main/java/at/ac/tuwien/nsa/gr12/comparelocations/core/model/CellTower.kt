package at.ac.tuwien.nsa.gr12.comparelocations.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CellTower(
    var radioType: String? = null,
    var mobileCountryCode: Int? = null,
    var mobileNetworkCode: Int? = null,
    var locationAreaCode: Int? = null,
    var cellId: Int? = null,
    var signalStrength: Int? = null
    ):Parcelable {
    constructor() : this(null, null, null, null, null, null)
}
