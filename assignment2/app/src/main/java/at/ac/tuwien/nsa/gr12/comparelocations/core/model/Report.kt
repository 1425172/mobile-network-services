package at.ac.tuwien.nsa.gr12.comparelocations.core.model

import java.util.*

class Report(
    var id: Long? = null,
    var date: Date? = null,
    var gpsLocation: Location? = null,
    var mlsLocation: Location? = null
) {
    constructor() : this(null, null, null, null)
}
