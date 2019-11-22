package at.ac.tuwien.nsa.gr12.comparelocations.core.ports

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.CellTower

interface CellTowersPort {

    fun get(): List<CellTower>
}