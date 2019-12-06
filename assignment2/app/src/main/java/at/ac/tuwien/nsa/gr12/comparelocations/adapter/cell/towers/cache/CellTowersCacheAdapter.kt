package at.ac.tuwien.nsa.gr12.comparelocations.adapter.cell.towers.cache

import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.CellTowersCacheInterface
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.CellTower

class CellTowersCacheAdapter : CellTowersCacheInterface {

    private var cellTowers: List<CellTower> = ArrayList()

    override fun set(cellTowers: List<CellTower>) {
        this.cellTowers = cellTowers
    }

    override fun get(): List<CellTower> {
        return cellTowers
    }
}