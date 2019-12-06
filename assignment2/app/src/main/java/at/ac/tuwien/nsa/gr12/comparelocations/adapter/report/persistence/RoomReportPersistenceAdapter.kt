package at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence

import android.content.Context
import android.text.SpannableStringBuilder
import androidx.room.Room
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence.mapper.ReportMapper
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.KeyStoreInterface
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.ReportPersistenceInterface
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report
import com.commonsware.cwac.saferoom.SafeHelperFactory
import org.mapstruct.factory.Mappers

class RoomReportPersistenceAdapter(context: Context, keyStore: KeyStoreInterface) : ReportPersistenceInterface {

    private val factory: SafeHelperFactory = SafeHelperFactory.fromUser(SpannableStringBuilder(keyStore.databaseKey()))

    private val reportDao = Room.databaseBuilder(
        context,
        ReportDatabase::class.java, "reports_encrypted"
    ).openHelperFactory(factory)
        .build()
        .reportDao()

    private val mapper = Mappers.getMapper(ReportMapper::class.java)

    override fun add(report: Report): Report {
        val entity = mapper.toEntity(report)

        val id = reportDao.insert(entity)

        entity.id = id
        return mapper.toModel(entity)
    }

    override fun getAll(): List<Report> {
        val entities = reportDao.getAll()
        return entities.map { mapper.toModel(it) }
    }

    override fun remove(report: Report) {
        val entity = mapper.toEntity(report)
        reportDao.delete(entity)
    }
}