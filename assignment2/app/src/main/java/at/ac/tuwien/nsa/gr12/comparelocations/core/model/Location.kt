package at.ac.tuwien.nsa.gr12.comparelocations.core.model

data class Location(
    var latitude: Double? = null,
    var longitude: Double? = null,
    var accuracy: Int? = null
) {
    constructor() : this(null, null, null)
}
