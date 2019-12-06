package at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases

import android.content.Intent
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.Mailinterface
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report

class MailUseCaseImpl(
    private val mailInterface: Mailinterface
) : MailUseCase {

    override suspend fun send(report: Report): Intent {
        return mailInterface.send(report)
    }
}