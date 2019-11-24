package at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report

interface Mailinterface {

    fun send(report: Report)
}