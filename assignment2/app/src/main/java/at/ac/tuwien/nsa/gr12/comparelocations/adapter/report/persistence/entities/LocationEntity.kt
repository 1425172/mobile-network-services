package at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence.entities

data class LocationEntity(
    var latitude: Double? = null,
    var longitude: Double? = null,
    var accuracy: Float? = null
) {
    constructor() : this(null, null, null)
}
