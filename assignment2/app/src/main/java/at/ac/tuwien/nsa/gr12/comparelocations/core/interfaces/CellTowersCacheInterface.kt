package at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.CellTower

interface CellTowersCacheInterface {

    fun set(cellTowers: List<CellTower>)

    fun get(): List<CellTower>
}