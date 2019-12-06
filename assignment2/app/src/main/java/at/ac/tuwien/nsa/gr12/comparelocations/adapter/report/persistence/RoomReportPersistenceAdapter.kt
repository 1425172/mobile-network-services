package at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence

import android.content.Context
import android.text.SpannableStringBuilder
import android.util.Log
import androidx.room.Room
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence.mapper.ReportMapper
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.KeyStoreInterface
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.ReportPersistenceInterface
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report
import com.commonsware.cwac.saferoom.SQLCipherUtils
import com.commonsware.cwac.saferoom.SafeHelperFactory
import org.mapstruct.factory.Mappers

class RoomReportPersistenceAdapter(
    private val context: Context,
    private val keyStore: KeyStoreInterface
) : ReportPersistenceInterface {

    private val preferencesFileKey = "PREFERENCES"
    private val encryptionEnabled = "ENCRYPTION_ENABLED"
    private val databaseName = "reports"

    private val factory: SafeHelperFactory =
        SafeHelperFactory.fromUser(SpannableStringBuilder(keyStore.databaseKey()))

    private var database: ReportDatabase
    private var reportDao: ReportDao
    private val mapper = Mappers.getMapper(ReportMapper::class.java)

    init {
        if (isEncrypted()) {
            this.database = getEncryptedDatabase()
        } else {
            this.database = getPlainDatabase()
        }
        this.reportDao = this.database.reportDao()
    }

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

    override fun encrypt() {
        if (isEncrypted()) {
            return
        }
        this.database.close()
        SQLCipherUtils.encrypt(
            this.context,
            databaseName,
            SpannableStringBuilder(this.keyStore.databaseKey())
        )
        this.database = getEncryptedDatabase()
        this.reportDao = this.database.reportDao()

        val sharedPreferences =
            this.context.getSharedPreferences(preferencesFileKey, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(encryptionEnabled, true)
        editor.apply()
    }

    override fun isEncrypted(): Boolean {
        val sharedPreferences =
            this.context.getSharedPreferences(preferencesFileKey, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(encryptionEnabled, false)
    }

    private fun getPlainDatabase(): ReportDatabase {
        return Room.databaseBuilder(
            context,
            ReportDatabase::class.java, databaseName
        ).build()
    }

    private fun getEncryptedDatabase(): ReportDatabase {
        return Room.databaseBuilder(
            context,
            ReportDatabase::class.java, databaseName
        ).openHelperFactory(factory)
            .build()
    }
}