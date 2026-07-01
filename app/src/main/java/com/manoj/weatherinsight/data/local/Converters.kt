package com.manoj.weatherinsight.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.manoj.weatherinsight.data.remote.ForecastDay
import com.manoj.weatherinsight.data.remote.Hour

/**
 * Type converters for Room to handle complex data types like Lists.
 * Uses GSON to serialize and deserialize objects into JSON strings.
 *
 * @author Manojkumar
 */
class Converters {

    private val gson = Gson()

    /**
     * Converts a list of [Hour] objects to a JSON string for database storage.
     */
    @TypeConverter
    fun fromHourList(hours: List<Hour>): String {
        return gson.toJson(hours)
    }

    /**
     * Converts a JSON string back into a list of [Hour] objects.
     */
    @TypeConverter
    fun toHourList(json: String): List<Hour> {
        val type = object : TypeToken<List<Hour>>() {}.type
        return gson.fromJson(json, type)
    }

    /**
     * Converts a list of [ForecastDay] objects to a JSON string for database storage.
     */
    @TypeConverter
    fun fromForecastDayList(days: List<ForecastDay>): String {
        return gson.toJson(days)
    }

    /**
     * Converts a JSON string back into a list of [ForecastDay] objects.
     */
    @TypeConverter
    fun toForecastDayList(json: String): List<ForecastDay> {
        val type = object : TypeToken<List<ForecastDay>>() {}.type
        return gson.fromJson(json, type)
    }
}
