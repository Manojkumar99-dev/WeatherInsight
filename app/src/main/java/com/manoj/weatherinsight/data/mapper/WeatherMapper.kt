package com.manoj.weatherinsight.data.mapper

import com.manoj.weatherinsight.data.local.WeatherEntity
import com.manoj.weatherinsight.data.remote.WeatherResponse

/**
 * Extension function to map a [WeatherResponse] from the API to a [WeatherEntity] for local storage.
 * This ensures that only relevant data is persisted and prepares it for the UI.
 * 
 * @return A [WeatherEntity] populated with data from the API response.
 * @author Manojkumar
 */
fun WeatherResponse.toWeatherEntity(): WeatherEntity {
    return WeatherEntity(
        city = location.name,
        country = location.country,
        temperature = current.temp_c,
        feelsLike = current.feelslike_c,
        humidity = current.humidity,
        windSpeed = current.wind_kph,
        pressure = current.pressure_mb,
        uv = current.uv,
        visibility = current.vis_km,
        weatherCondition = current.condition.text,
        // Ensure the icon URL is properly prefixed with https:
        weatherIcon = "https:${current.condition.icon}",
        // Extract hourly data from the first day of forecast.
        hourlyForecast = forecast.forecastday.firstOrNull()?.hour ?: emptyList(),
        // Map the entire weekly forecast.
        weeklyForecast = forecast.forecastday,
        // Set the current timestamp as the last updated time.
        lastUpdated = System.currentTimeMillis()
    )
}
