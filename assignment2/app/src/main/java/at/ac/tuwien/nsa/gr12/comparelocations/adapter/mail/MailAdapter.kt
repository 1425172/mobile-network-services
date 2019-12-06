package at.ac.tuwien.nsa.gr12.comparelocations.adapter.mail

import android.content.Intent
import android.net.Uri
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.mail.mapper.MailMapper
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.Mailinterface
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report
import com.google.gson.GsonBuilder
import org.mapstruct.factory.Mappers


class MailAdapter : Mailinterface {

    private val mapper = Mappers.getMapper(MailMapper::class.java)
    private val gson = GsonBuilder().setPrettyPrinting().create()

    override fun send(report: Report): Intent {
        val mailReport = mapper.map(report)

        val subject = "Report from " + mailReport.date!!.toString()
        val body = gson.toJson(mailReport)

        return Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_EMAIL, ArrayList<String>())
            putExtra(Intent.EXTRA_TEXT, body)
        }
    }
}