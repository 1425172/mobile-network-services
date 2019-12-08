package at.ac.tuwien.nsa.gr12.comparelocations.test.adapter

import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.CellTowersCacheInterface
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.CellTower

class CellTowersCacheTestAdapter : CellTowersCacheInterface {

    override fun set(cellTowers: List<CellTower>) {
    }

    override fun get(): List<CellTower> {
        return listOf(CellTower("radio", 1, 2, 3, 4, 5))
    }
}