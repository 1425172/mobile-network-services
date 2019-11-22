package at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.model

class MLSWifiAccessPoint(
    var macAddress: String? = null,
    var signalStrength: Int? = null
) {
    constructor() : this(null, null)
}