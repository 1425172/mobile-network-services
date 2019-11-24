package at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases

import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.*
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report
import java.util.*

class ReportService(
    private val cellTowersInterface: CellTowersInterface,
    private val gpsInterface: GPSInterface,
    private val locationServiceInterface: LocationServiceInterface,
    private val reportPersistenceInterface: ReportPersistenceInterface,
    private val wifiInterface: WifiInterface
) : ReportUseCase {

    override suspend fun getNew(): Report {
        val cellTowersAsync = cellTowersInterface.getAsync()
        val accessPointsAsync = wifiInterface.getAsync()
        val gpsLocationAsync = gpsInterface.getAsync()

        val accessPoints = accessPointsAsync.await()
        val cellTowers = cellTowersAsync.await()
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