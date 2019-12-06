package at.ac.tuwien.nsa.gr12.comparelocations.adapter.mail.model

data class AccessPointMailEntity(
    var macAddress: String? = null,
    var signalStrength: Int? = null
) {
    constructor() : this(null, null)
}
