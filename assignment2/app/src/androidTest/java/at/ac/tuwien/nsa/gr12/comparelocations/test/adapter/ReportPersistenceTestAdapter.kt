package at.ac.tuwien.nsa.gr12.comparelocations.test.adapter

import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.ReportPersistenceInterface
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report
import java.util.stream.Collectors

class ReportPersistenceTestAdapter : ReportPersistenceInterface {

    private val reports: MutableList<Report> = ArrayList()

    override fun add(report: Report): Report {
        report.id = reports.size.toLong()
        reports.add(report)
        return report
    }

    override fun getAll(): List<Report> {
        return reports.stream().collect(Collectors.toList())
    }

    override fun remove(report: Report) {
        reports.remove(report)
    }

    override fun encrypt() {
    }

    override fun isEncrypted(): Boolean {
        return true
    }
}