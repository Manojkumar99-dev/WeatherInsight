package com.manoj.weatherinsight.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * The Room database for this app.
 * Persists [WeatherEntity] objects for offline access and caching.
 *
 * @property weatherDao Access to weather database operations.
 * @author Manojkumar
 */
@Database(
    entities = [WeatherEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WeatherDatabase : RoomDatabase() {

    /**
     * Gets the DAO for weather operations.
     */
    abstract fun weatherDao(): WeatherDao
}
