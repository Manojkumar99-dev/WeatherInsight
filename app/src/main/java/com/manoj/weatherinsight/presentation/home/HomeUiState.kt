package com.manoj.weatherinsight.presentation.home

import com.manoj.weatherinsight.data.local.WeatherEntity

/**
 * Data class representing the UI state for the Home screen.
 *
 * @property searchCity The city name being searched or currently displayed.
 * @property weather The weather data fetched for the city.
 * @property error Error message to display if an operation fails.
 * @property isLoading Indicates if a standard loading operation (e.g., initial search) is active.
 * @property isRefreshing Indicates if a pull-to-refresh operation is active.
 * @author Manojkumar
 */
data class HomeUiState(
    val searchCity: String = "Bangalore",
    val weather: WeatherEntity? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false
)
