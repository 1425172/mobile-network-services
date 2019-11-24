package at.ac.tuwien.nsa.gr12.comparelocations

import android.app.Application
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.cell.tower.CellTowersAdapter
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.gps.location.service.GPSAdapter
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mail.MailAdapter
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service.MozillaLocationServiceAdapter
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence.ReportSQLLiteAdpater
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.wifi.WifiAdapter
import at.ac.tuwien.nsa.gr12.comparelocations.core.ports.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class CompareLocationsApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        bind<CellTowersPort>() with singleton { CellTowersAdapter(applicationContext) }
        bind<GPSPort>() with singleton { GPSAdapter(applicationContext) }
        bind<MailPort>() with singleton { MailAdapter() }
        bind<MozillaLocationServicePort>() with singleton { MozillaLocationServiceAdapter() }
        bind<ReportPersistencePort>() with singleton { ReportSQLLiteAdpater() }
        bind<WifiPort>() with singleton { WifiAdapter(applicationContext) }
    }
}