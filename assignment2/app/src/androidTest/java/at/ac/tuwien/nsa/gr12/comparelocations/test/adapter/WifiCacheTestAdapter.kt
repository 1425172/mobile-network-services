package at.ac.tuwien.nsa.gr12.comparelocations.test.adapter

import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.WifiCacheInterface
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.AccessPoint

class WifiCacheTestAdapter : WifiCacheInterface {
    override fun set(accessPoints: List<AccessPoint>) {
    }

    override fun get(): List<AccessPoint> {
        return listOf(AccessPoint("xxxx", 12))
    }
}