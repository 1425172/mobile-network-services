package at.ac.tuwien.nsa.gr12.comparelocations.core.model

class AccessPoint(
    var macAddress: String? = null,
    var signalStrength: Int? = null
) {
    constructor() : this(null, null)
}
