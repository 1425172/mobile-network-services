package at.ac.tuwien.nsa.gr12.comparelocations.core.ports

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report

interface MailPort {

    fun send(report: Report)
}