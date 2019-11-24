package at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.AccessPoint
import kotlinx.coroutines.Deferred

interface WifiInterface {

    suspend fun getAsync(): Deferred<List<AccessPoint>>
}