package at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Location
import kotlinx.coroutines.Deferred

interface GPSInterface {

    fun getAsync(): Deferred<Location>
}