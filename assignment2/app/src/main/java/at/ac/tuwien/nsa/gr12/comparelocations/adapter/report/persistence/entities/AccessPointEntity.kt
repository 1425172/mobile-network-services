package at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence.entities

data class AccessPointEntity(
    var macAddress: String? = null,
    var signalStrength: Int? = null

) {
    constructor() : this(null, null)
}
