package at.ac.tuwien.nsa.gr12.comparelocations.adapter.mail.model

import java.util.*

data class ReportMailEntitiy(
    var id: Long? = null,
    var date: Date? = null,
    var gpsLocation: LocationMailEntity? = null,
    var accessPoints: List<AccessPointMailEntity> = mutableListOf(),
    var cellTowers: List<CellTowerMailEntity> = mutableListOf(),
    var mlsLocation: LocationMailEntity? = null,
    var distance: Float?
) {
    constructor() : this(null, null, null, mutableListOf(), mutableListOf(), null, null)
}


