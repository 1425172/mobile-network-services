package at.ac.tuwien.nsa.gr12.comparelocations

import android.app.Application
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.cell.tower.CellTowersAdapter
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.gps.location.service.GPSAdapter
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mail.MailAdapter
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.MozillaLocationServiceAdapter
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence.ReportSQLLiteAdpater
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.wifi.WifiAdapter
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.*
import at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases.ReportService
import at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases.ReportUseCase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class CompareLocationsApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        bind<CellTowersInterface>() with singleton { CellTowersAdapter(applicationContext) }
        bind<GPSInterface>() with singleton { GPSAdapter(applicationContext) }
        bind<Mailinterface>() with singleton { MailAdapter() }
        bind<LocationServiceInterface>() with singleton { MozillaLocationServiceAdapter() }
        bind<ReportPersistenceInterface>() with singleton { ReportSQLLiteAdpater() }
        bind<WifiInterface>() with singleton { WifiAdapter(applicationContext) }

        bind<ReportUseCase>() with singleton {
            ReportService(instance(), instance(), instance(), instance(), instance())
        }
    }
}