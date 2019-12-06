package at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases

import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.*
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report
import java.util.*

class ReportUseCaseImpl(
    private val cellTowersCache: CellTowersCacheInterface,
    private val gpsInterface: GPSInterface,
    private val locationServiceInterface: LocationServiceInterface,
    private val reportPersistenceInterface: ReportPersistenceInterface,
    private val wifiCache: WifiCacheInterface
) : ReportUseCase {

    override suspend fun getNew(): Report {
        val gpsLocationAsync = gpsInterface.getAsync()

        val accessPoints = wifiCache.get()
        val cellTowers = cellTowersCache.get()
        val mlsLocation = locationServiceInterface.get(accessPoints, cellTowers)

        val gpsLocation = gpsLocationAsync.await()
        val report =  Report(null, Date(), gpsLocation, accessPoints, cellTowers, mlsLocation)

        return reportPersistenceInterface.add(report)
    }

    override suspend fun getAll(): List<Report> {
        return reportPersistenceInterface.getAll()
    }

    override suspend fun remove(report: Report) {
        reportPersistenceInterface.remove(report)
    }
}