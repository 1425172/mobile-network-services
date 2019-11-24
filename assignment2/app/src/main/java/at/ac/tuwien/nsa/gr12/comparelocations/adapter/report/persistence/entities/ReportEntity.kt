package at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "report")
data class ReportEntity(
    @PrimaryKey var id: Long? = null,
    var date: Date? = null,
    @Embedded(prefix = "gps") var gpsLocation: LocationEntity? = null,
    var accessPoints: ArrayList<AccessPointEntity>? = null,
    var cellTowers: ArrayList<CellTowerEntity>? = null,
    @Embedded(prefix = "mls") var mlsLocation: LocationEntity? = null
) {
    constructor() : this(null, null, null, null, null, null)
}
