package com.manoj.weatherinsight.domain.repository

import com.manoj.weatherinsight.core.util.Resource
import com.manoj.weatherinsight.data.local.WeatherEntity
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the contract for weather data operations.
 * Decouples the domain layer from data implementation details.
 *
 * @author Manojkumar
 */
interface WeatherRepository {

    /**
     * Observes local weather data for a specific city.
     * 
     * @param city The name of the city.
     * @return A [Flow] emitting the cached [WeatherEntity], if any.
     */
    fun getWeather(city: String): Flow<WeatherEntity?>

    /**
     * Fetches fresh weather data from the remote API and updates the local cache.
     * 
     * @param city The name of the city.
     * @return A [Resource] indicating the result of the network operation.
     */
    suspend fun refreshWeather(city: String): Resource<Unit>
}
