package at.ac.tuwien.nsa.gr12.comparelocations.core.model

data class Location(
    var latitude: Double? = null,
    var longitude: Double? = null,
    var accuracy: Float? = null
) {
    constructor() : this(null, null, null)

    override fun toString(): String {
        return "Location(latitude=$latitude, longitude=$longitude, accuracy=$accuracy)"
    }


}
