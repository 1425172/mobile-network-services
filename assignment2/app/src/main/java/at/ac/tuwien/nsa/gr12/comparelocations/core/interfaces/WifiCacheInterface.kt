package at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.AccessPoint

interface WifiCacheInterface {

    fun set(accessPoints: List<AccessPoint>)

    fun get(): List<AccessPoint>
}