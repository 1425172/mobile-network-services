package at.ac.tuwien.nsa.gr12.comparelocations.core.model

data class CellTower(
    val radioType: String,
    val mobileCountryCode: Int,
    val mobileNetworkCode: Int,
    val locationAreaCode: Int,
    val cellId: Int,
    val signalStrength: Int
)
