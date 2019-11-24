package at.ac.tuwien.nsa.gr12.comparelocations.core.ports

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.CellTower
import kotlinx.coroutines.Deferred

interface CellTowersPort {

    fun getAsync(): Deferred<List<CellTower>>
}