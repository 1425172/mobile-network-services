package at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.CellTower
import kotlinx.coroutines.Deferred

interface CellTowersInterface {

    fun getAsync(): Deferred<List<CellTower>>
}