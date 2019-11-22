package at.ac.tuwien.nsa.gr12.comparelocations.core.ports

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report

interface ReportPersistencePort {
    fun add(report: Report)

    fun get(id: Long): Report

    fun getAll(): List<Report>

    fun remove(report: Report)
}