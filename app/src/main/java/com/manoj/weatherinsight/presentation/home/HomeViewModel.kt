package com.manoj.weatherinsight.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.content.Context
import com.manoj.weatherinsight.R
import com.manoj.weatherinsight.core.util.Resource
import com.manoj.weatherinsight.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Home screen.
 * Orchestrates weather data fetching, manual search, and pull-to-refresh logic.
 *
 * @author Manojkumar
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    /**
     * Loads weather data for a specific city.
     * Updates [isLoading] and [error] states during the process.
     */
    fun loadWeather(city: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            when (val result = getWeatherUseCase.refresh(city)) {
                is Resource.Success -> {
                    // Observe the fresh data from the local database.
                    val weather = getWeatherUseCase(city).first()

                    if (weather != null) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            weather = weather,
                            searchCity = weather.city,
                            error = null
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = context.getString(R.string.error_city_not_found)
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }

                is Resource.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        }
    }

    /**
     * Updates the search city query in the UI state.
     */
    fun onCityChanged(city: String) {
        _uiState.value = _uiState.value.copy(searchCity = city)
    }

    /**
     * Triggers a weather search using the current query in [searchCity].
     */
    fun searchWeather() {
        loadWeather(_uiState.value.searchCity)
    }

    /**
     * Performs a manual refresh of weather data for the current city.
     * Updates [isRefreshing] state specifically for the pull-to-refresh UI.
     */
    fun refreshWeather() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isRefreshing = true,
                error = null
            )

            when (val result = getWeatherUseCase.refresh(_uiState.value.searchCity)) {
                is Resource.Success -> {
                    val weather = getWeatherUseCase(_uiState.value.searchCity).first()
                    _uiState.value = _uiState.value.copy(
                        isRefreshing = false,
                        weather = weather,
                        error = null
                    )
                }

                is Resource.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isRefreshing = false,
                        error = result.message
                    )
                }

                else -> {
                    _uiState.value = _uiState.value.copy(isRefreshing = false)
                }
            }
        }
    }
}
