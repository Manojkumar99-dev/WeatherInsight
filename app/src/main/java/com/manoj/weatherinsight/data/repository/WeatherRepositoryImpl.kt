package com.manoj.weatherinsight.data.repository

import android.content.Context
import com.manoj.weatherinsight.core.constants.AppConstants
import com.manoj.weatherinsight.data.local.WeatherDao
import com.manoj.weatherinsight.data.local.WeatherEntity
import com.manoj.weatherinsight.data.mapper.toWeatherEntity
import com.manoj.weatherinsight.data.remote.WeatherApi
import com.manoj.weatherinsight.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.manoj.weatherinsight.BuildConfig
import com.manoj.weatherinsight.core.util.ErrorUtils
import com.manoj.weatherinsight.core.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber

/**
 * Implementation of [WeatherRepository].
 * Handles the logic of fetching weather from API and caching it in the Room database.
 *
 * @author Manojkumar
 */
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao,
    @ApplicationContext private val context: Context
) : WeatherRepository {


    /**
     * Retrieves weather data from the local database.
     */
    override fun getWeather(city: String): Flow<WeatherEntity?> {
        return weatherDao.getWeather(city)
    }

    /**
     * Refreshes weather data from the API if the cache is empty or expired.
     * Implements a Time-To-Live (TTL) strategy for caching.
     */
    override suspend fun refreshWeather(city: String): Resource<Unit> {
        return try {

            val cachedWeather = weatherDao.getWeatherOnce(city)

            // Smart TTL Refresh:
            // Skip the network call if the cached weather is still fresh.
            val shouldRefresh =
                cachedWeather == null ||
                        System.currentTimeMillis() - cachedWeather.lastUpdated > AppConstants.CACHE_TTL_MS

            if (!shouldRefresh) {
                return Resource.Success(Unit)
            }

            val response = weatherApi.getWeatherForecast(
                apiKey = BuildConfig.WEATHER_API_KEY,
                city = city.trim(),
                days = AppConstants.FORECAST_DAYS
            )

            weatherDao.insertWeather(
                response.toWeatherEntity()
            )

            Resource.Success(Unit)

        } catch (e: Exception) {

            Timber.e(e)

            Resource.Error(
                context.getString(ErrorUtils.getMessage(e))
            )
        }
    }
}
