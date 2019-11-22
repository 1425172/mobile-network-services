package at.ac.tuwien.nsa.gr12.comparelocations.adapter.mozilla.location.service

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.AccessPoint
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.CellTower
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Location
import at.ac.tuwien.nsa.gr12.comparelocations.core.ports.MozillaLocationServicePort

class MozillaLocationServiceAdapter : MozillaLocationServicePort {
    override fun get(accessPoints: List<AccessPoint>, cellTowers: List<CellTower>): Location {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}