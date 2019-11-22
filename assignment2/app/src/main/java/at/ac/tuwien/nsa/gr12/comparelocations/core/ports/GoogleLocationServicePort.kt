package at.ac.tuwien.nsa.gr12.comparelocations.core.ports

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Location

interface GoogleLocationServicePort {

    fun get(): Location
}