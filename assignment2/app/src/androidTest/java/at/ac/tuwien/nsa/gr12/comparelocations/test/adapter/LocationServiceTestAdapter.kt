package at.ac.tuwien.nsa.gr12.comparelocations.test.adapter

import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.LocationServiceInterface
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.AccessPoint
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.CellTower
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Location

class LocationServiceTestAdapter : LocationServiceInterface {
    override suspend fun get(
        accessPoints: List<AccessPoint>,
        cellTowers: List<CellTower>
    ): Location {
        return Location(0.0, 0.0, 0f)
    }
}