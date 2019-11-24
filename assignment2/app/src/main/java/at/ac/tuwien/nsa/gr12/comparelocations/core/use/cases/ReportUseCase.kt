package at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report

interface ReportUseCase {

    suspend fun getNew(): Report

    suspend fun getAll(): List<Report>

    suspend fun delete(report: Report)
}