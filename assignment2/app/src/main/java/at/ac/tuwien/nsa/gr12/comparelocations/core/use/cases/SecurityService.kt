package at.ac.tuwien.nsa.gr12.comparelocations.core.use.cases

import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.ReportPersistenceInterface

class SecurityService(private val persistence: ReportPersistenceInterface) : SecurityUseCase{
    override fun encryptDatabase() {
        this.persistence.encrypt()
    }

    override fun databaseIsEncrypted(): Boolean {
        return this.persistence.isEncrypted()
    }
}