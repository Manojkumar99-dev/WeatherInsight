package com.manoj.weatherinsight.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface defining the API endpoints for fetching weather data.
 *
 * @author Manojkumar
 */
interface WeatherApi {

    /**
     * Fetches the weather forecast for a given city.
     * 
     * @param apiKey The API key for WeatherAPI.com.
     * @param city The city name or coordinates (e.g., "Bangalore").
     * @param days Number of forecast days to fetch.
     * @param aqi Whether to include air quality data (default "yes").
     * @param alerts Whether to include weather alerts (default "yes").
     * @return A [WeatherResponse] object containing forecast data.
     */
    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String = "yes",
        @Query("alerts") alerts: String = "yes"
    ): WeatherResponse
}
