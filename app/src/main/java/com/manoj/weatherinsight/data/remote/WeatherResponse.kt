package com.manoj.weatherinsight.data.remote

import com.google.gson.annotations.SerializedName

/**
 * Root response object from the Weather API.
 *
 * @author Manojkumar
 */
data class WeatherResponse(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)

/**
 * Information about the location being queried.
 */
data class Location(
    val name: String,
    val country: String
)

/**
 * Current weather conditions.
 */
data class Current(
    val temp_c: Double,
    val feelslike_c: Double,
    val humidity: Int,
    val wind_kph: Double,
    val pressure_mb: Double,
    val uv: Double,
    val vis_km: Double,
    val condition: Condition
)

/**
 * Container for forecast days.
 */
data class Forecast(
    val forecastday: List<ForecastDay>
)

/**
 * Represents a single day's forecast, including daily totals and hourly data.
 */
data class ForecastDay(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)

/**
 * Summarized daily weather data (max/min temps, overall condition).
 */
data class Day(

    val maxtemp_c: Double,

    val mintemp_c: Double,

    @SerializedName("daily_chance_of_rain")
    val dailyChanceOfRain: Int = 0,

    val condition: Condition
)

/**
 * Hourly weather data.
 */
data class Hour(
    val time: String,
    val temp_c: Double,
    val condition: Condition
)

/**
 * Descriptive weather condition (text and icon URL).
 */
data class Condition(
    val text: String,
    val icon: String
)
