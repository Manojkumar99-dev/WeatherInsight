package com.manoj.weatherinsight.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for the [WeatherEntity] table.
 * Defines the database operations for weather data.
 *
 * @author Manojkumar
 */
@Dao
interface WeatherDao {

    /**
     * Inserts or replaces weather data in the database.
     * 
     * @param weather The weather entity to save.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)

    /**
     * Observes weather data for a specific city.
     * 
     * @param city The name of the city.
     * @return A [Flow] that emits the weather data whenever it changes.
     */
    @Query("""
    SELECT * FROM weather
    WHERE LOWER(city) = LOWER(:city)
""")
    fun getWeather(city: String): Flow<WeatherEntity?>

    /**
     * Deletes weather data for a specific city.
     * 
     * @param city The name of the city.
     */
    @Query("""
    DELETE FROM weather
    WHERE LOWER(city) = LOWER(:city)
""")
    suspend fun deleteWeather(city: String)

    /**
     * Clears all weather data from the table.
     */
    @Query("DELETE FROM weather")
    suspend fun clearWeather()

    @Query("SELECT * FROM weather WHERE LOWER(city)=LOWER(:city)")
    suspend fun getWeatherOnce(city: String): WeatherEntity?
}
