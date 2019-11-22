package at.ac.tuwien.nsa.gr12.comparelocations.core.model

import java.util.*

data class Report(
    val id: Long,
    val date: Date,
    val gpsLocation: Location,
    val mlsLocation: Location
)
