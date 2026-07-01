package com.manoj.weatherinsight.data.repository

import com.manoj.weatherinsight.data.local.WeatherDao
import com.manoj.weatherinsight.data.local.WeatherEntity
import com.manoj.weatherinsight.data.remote.WeatherApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryImplTest {

    private lateinit var repository: WeatherRepositoryImpl

    private val weatherApi: WeatherApi = mock()

    private val weatherDao: WeatherDao = mock()

    @Before
    fun setup() {
        repository = WeatherRepositoryImpl(
            weatherApi = weatherApi,
            weatherDao = weatherDao
        )
    }

    /**
     * Verifies that weather data is requested
     * from the local database.
     */
    @Test
    fun getWeather_shouldReturnDataFromDao() = runTest {

        whenever(weatherDao.getWeather("Bangalore"))
            .thenReturn(emptyFlow())

        repository.getWeather("Bangalore")

        verify(weatherDao).getWeather("Bangalore")
    }

    /**
     * Verifies that cached weather
     * is checked before making an API call.
     */
    @Test
    fun refreshWeather_shouldCheckCacheBeforeApiCall() = runTest {

        whenever(weatherDao.getWeatherOnce("Bangalore"))
            .thenReturn(createWeatherEntity())

        repository.refreshWeather("Bangalore")

        verify(weatherDao).getWeatherOnce("Bangalore")

        verify(weatherApi, never()).getWeatherForecast(
            any(),
            any(),
            any(),
            any(),
            any()
        )
    }

    private fun createWeatherEntity() = WeatherEntity(
        city = "Bangalore",
        country = "India",
        temperature = 30.0,
        feelsLike = 32.0,
        humidity = 65,
        windSpeed = 15.0,
        pressure = 1008.0,
        uv = 6.0,
        visibility = 10.0,
        weatherCondition = "Sunny",
        weatherIcon = "",
        hourlyForecast = emptyList(),
        weeklyForecast = emptyList(),
        lastUpdated = System.currentTimeMillis(),
        lastRefresh = System.currentTimeMillis()
    )
}

