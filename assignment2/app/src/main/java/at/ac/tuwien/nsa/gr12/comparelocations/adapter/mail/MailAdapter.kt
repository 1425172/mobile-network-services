package at.ac.tuwien.nsa.gr12.comparelocations.adapter.mail

import android.content.Intent
import android.net.Uri
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.Mailinterface
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report
import com.google.gson.GsonBuilder


class MailAdapter : Mailinterface {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    override fun send(report: Report): Intent {
        val subject = "Report from " + report.date!!.toString()
        val body = gson.toJson(report)

        return Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_EMAIL, ArrayList<String>())
            putExtra(Intent.EXTRA_TEXT, body)
        }
    }
}