package at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence

import androidx.room.TypeConverter
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence.entities.AccessPointEntity
import at.ac.tuwien.nsa.gr12.comparelocations.adapter.report.persistence.entities.CellTowerEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun accessPointsToJson(accessPoints: ArrayList<AccessPointEntity>?): String {
        val gson = Gson()
        return gson.toJson(accessPoints)
    }

    @TypeConverter
    fun accessPointsFromJson(json: String?): ArrayList<AccessPointEntity> {
        val gson = Gson()
        val collectionType: Type = object : TypeToken<ArrayList<AccessPointEntity>?>() {}.type
        return gson.fromJson(json, collectionType)
    }

    @TypeConverter
    fun cellTowersToJson(accessPoints: ArrayList<CellTowerEntity>?): String {
        val gson = Gson()
        return gson.toJson(accessPoints)
    }

    @TypeConverter
    fun cellTowersFromJson(json: String?): ArrayList<CellTowerEntity>? {
        val gson = Gson()
        val collectionType: Type = object : TypeToken<ArrayList<CellTowerEntity>?>() {}.type
        return gson.fromJson(json, collectionType)
    }
}
