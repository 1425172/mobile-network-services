package at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces

interface KeyStoreInterface {

    fun databaseKey(): String

    fun mlsKey(): String
}