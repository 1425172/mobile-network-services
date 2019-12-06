package at.ac.tuwien.nsa.gr12.comparelocations.adapter.mail.model

data class CellTowerMailEntity(
    var radioType: String? = null,
    var mobileCountryCode: Int? = null,
    var mobileNetworkCode: Int? = null,
    var locationAreaCode: Int? = null,
    var cellId: Int? = null,
    var signalStrength: Int? = null
    ) {
    constructor() : this(null, null, null, null, null, null)
}
