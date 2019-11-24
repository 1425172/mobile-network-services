package at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.model

class MLSResponse(
    var location: MLSLocation? = null,
    var accuracy: Float? = null
) {
    constructor() : this(null, null)
}