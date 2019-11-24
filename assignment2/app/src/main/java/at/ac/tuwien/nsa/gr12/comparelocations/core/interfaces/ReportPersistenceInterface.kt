package at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report

interface ReportPersistenceInterface {
    fun add(report: Report)

    fun get(id: Long): Report

    fun getAll(): List<Report>

    fun remove(report: Report)
}