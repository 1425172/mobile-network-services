package at.ac.tuwien.nsa.gr12.comparelocations.core.ports

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.AccessPoint
import kotlinx.coroutines.Deferred

interface WifiPort {

    suspend fun getAsync(): Deferred<List<AccessPoint>>
}