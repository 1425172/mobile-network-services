package at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence.entities.ReportEntity

@Dao
interface ReportDao {

    @Insert
    fun insert(report: ReportEntity): Long

    @Query("SELECT * FROM report")
    fun getAll(): Array<ReportEntity>

    @Delete
    fun delete(report: ReportEntity)
}