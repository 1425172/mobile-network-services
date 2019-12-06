package at.ac.tuwien.nsa.gr12.comparelocations.adapter.wifi.cache

import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.WifiCacheInterface
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.AccessPoint

class WifiCacheAdapter : WifiCacheInterface {

    private var accessPoints: List<AccessPoint> = ArrayList()

    override fun set(accessPoints: List<AccessPoint>) {
        this.accessPoints = accessPoints
    }

    override fun get(): List<AccessPoint> {
        return this.accessPoints
    }
}