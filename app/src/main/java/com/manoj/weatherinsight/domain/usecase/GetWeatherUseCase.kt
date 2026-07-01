package com.manoj.weatherinsight.domain.usecase

import com.manoj.weatherinsight.core.util.Resource
import com.manoj.weatherinsight.data.local.WeatherEntity
import com.manoj.weatherinsight.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving and refreshing weather data.
 * Encapsulates the interaction between ViewModels and the Repository.
 *
 * @author Manojkumar
 */
class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    /**
     * Executes the use case to observe weather data.
     */
    operator fun invoke(city: String): Flow<WeatherEntity?> {
        return repository.getWeather(city)
    }

    /**
     * Triggers a manual refresh of weather data.
     */
    suspend fun refresh(city: String): Resource<Unit> {
        return repository.refreshWeather(city)
    }
}
