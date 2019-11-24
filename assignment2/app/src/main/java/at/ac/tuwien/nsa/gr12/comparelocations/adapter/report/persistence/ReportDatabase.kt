package at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence.entities.*

@Database(
    entities = [
        ReportEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ReportDatabase : RoomDatabase() {
    abstract fun reportDao(): ReportDao
}