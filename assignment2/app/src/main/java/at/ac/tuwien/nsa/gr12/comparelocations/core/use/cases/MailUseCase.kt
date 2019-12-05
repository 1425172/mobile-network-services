package at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases

import android.content.Intent
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report

interface MailUseCase {

    suspend fun send(report: Report): Intent
}