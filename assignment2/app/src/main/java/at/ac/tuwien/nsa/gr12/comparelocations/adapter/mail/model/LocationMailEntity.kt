package at.ac.tuwien.nsa.gr12.comparelocations.adapter.mail.model

data class LocationMailEntity(
    var latitude: Double? = null,
    var longitude: Double? = null,
    var accuracy: Float? = null
) {
    constructor() : this(null, null, null)
}
