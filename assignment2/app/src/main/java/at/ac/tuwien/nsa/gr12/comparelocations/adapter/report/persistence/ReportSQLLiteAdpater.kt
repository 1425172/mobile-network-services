package at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence

import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.ReportPersistenceInterface

class ReportSQLLiteAdpater : ReportPersistenceInterface {
    override fun add(report: Report) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(id: Long): Report {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): List<Report> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(report: Report) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}