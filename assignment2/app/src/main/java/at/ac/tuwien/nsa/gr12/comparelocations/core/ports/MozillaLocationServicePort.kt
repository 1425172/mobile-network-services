package at.ac.tuwien.nsa.gr12.comparelocations.core.ports

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.AccessPoint
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.CellTower
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Location

interface MozillaLocationServicePort {

    fun get(accessPoints: List<AccessPoint>, cellTowers: List<CellTower>): Location
}