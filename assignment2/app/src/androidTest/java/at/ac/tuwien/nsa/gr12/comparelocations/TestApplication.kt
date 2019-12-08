package at.ac.tuwien.nsa.gr12.comparelocations

import android.app.Application
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.cell.towers.CellTowersAdapter
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.key.store.AndroidKeyStoreAdapter
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mail.MailAdapter
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.wifi.WifiAdapter
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.*
import at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases.*
import at.ac.tuwien.nsa.gr12.comparelocations.test.adapter.*
import at.ac.tuwien.nsa.gr12.comparelocations.ui.main.ReportListViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class TestApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        bind<KeyStoreInterface>() with singleton { AndroidKeyStoreAdapter() }
        bind<CellTowersInterface>() with singleton { CellTowersAdapter(applicationContext) }
        bind<GPSInterface>() with singleton { GPSTestAdapter() }
        bind<Mailinterface>() with singleton { MailAdapter() }
        bind<LocationServiceInterface>() with singleton { LocationServiceTestAdapter() }
        bind<ReportPersistenceInterface>() with singleton { ReportPersistenceTestAdapter() }
        bind<WifiInterface>() with singleton { WifiAdapter(applicationContext) }
        bind<WifiCacheInterface>() with singleton { WifiCacheTestAdapter() }
        bind<CellTowersCacheInterface>() with singleton { CellTowersCacheTestAdapter() }

        bind<ReportUseCase>() with singleton {
            ReportUseCaseImpl(instance(), instance(), instance(), instance(), instance())
        }
        bind<MailUseCase>() with singleton { MailUseCaseImpl(instance()) }
        bind<SecurityUseCase>() with singleton { SecurityUseCaseImpl(instance()) }

        bind<ReportListViewModel>() with singleton { ReportListViewModel(instance()) }
    }
}