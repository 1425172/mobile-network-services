package at.ac.tuwien.nsa.gr12.comparelocations.test.adapter

import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.GPSInterface
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Location
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

class GPSTestAdapter : GPSInterface {
    override fun getAsync(): Deferred<Location> {
        val deferred = CompletableDeferred<Location>()
        deferred.complete(Location(1.0, 1.0, 1f))
        return deferred
    }
}