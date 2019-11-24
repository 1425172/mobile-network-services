package at.ac.tuwien.nsa.gr12.comparelocations.core.ports

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Location
import kotlinx.coroutines.Deferred

interface GPSPort {

    fun getAsync(): Deferred<Location>
}