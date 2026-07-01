package com.manoj.weatherinsight.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.manoj.weatherinsight.data.remote.ForecastDay
import com.manoj.weatherinsight.data.remote.Hour

/**
 * Room Entity representing weather data for a specific city.
 * This class is used for caching weather information locally in the database.
 *
 * @author Manojkumar
 */
@Entity(tableName = "weather")
data class WeatherEntity(

    /** The name of the city, used as the Primary Key. */
    @PrimaryKey
    val city: String,

    /** The country where the city is located. */
    val country: String,

    /** Current temperature in Celsius. */
    val temperature: Double,

    /** Feels-like temperature in Celsius. */
    val feelsLike: Double,

    /** Humidity percentage. */
    val humidity: Int,

    /** Wind speed in km/h. */
    val windSpeed: Double,

    /** Atmospheric pressure in millibars (mb). */
    val pressure: Double,

    /** UV index. */
    val uv: Double,

    /** Visibility in kilometers (km). */
    val visibility: Double,

    /** Description of current weather (e.g., "Sunny", "Cloudy"). */
    val weatherCondition: String,

    /** URL for the weather icon. */
    val weatherIcon: String,

    /** List of hourly forecast data. */
    val hourlyForecast: List<Hour>,

    /** List of daily forecast data for the week. */
    val weeklyForecast: List<ForecastDay>,

    /** Timestamp of the last local update (milliseconds). */
    val lastUpdated: Long,

    @ColumnInfo(name = "last_refresh")
    val lastRefresh: Long = System.currentTimeMillis()
)
