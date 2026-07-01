package com.manoj.weatherinsight.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manoj.weatherinsight.core.util.WeatherAlertUtil
import com.manoj.weatherinsight.presentation.components.ErrorView
import com.manoj.weatherinsight.presentation.components.WeatherAlertCard
import com.manoj.weatherinsight.presentation.home.components.CurrentWeatherCard
import com.manoj.weatherinsight.presentation.home.components.HourlyForecastSection
import com.manoj.weatherinsight.presentation.home.components.SearchBar
import com.manoj.weatherinsight.presentation.home.components.WeatherDetailsSection
import com.manoj.weatherinsight.presentation.home.components.WeeklyForecastSection

/**
 * The main screen of the application.
 * Displays current weather, hourly forecast, weekly forecast, and a search bar.
 * Supports pull-to-refresh to update the weather data.
 *
 * @param viewModel The [HomeViewModel] that provides state and logic.
 * @author Manojkumar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    snackbarHostState: SnackbarHostState,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Trigger initial weather load for the default city if data is missing.
    LaunchedEffect(Unit) {
        if (state.weather == null) {
            viewModel.loadWeather(state.searchCity)
        }
    }
    LaunchedEffect(state.error) {

        if (state.error != null && state.weather != null) {

            snackbarHostState.showSnackbar(
                message = state.error!!
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        state.error?.let { error ->

            if (state.weather == null) {

                ErrorView(
                    message = error,
                    onRetry = {
                        viewModel.searchWeather()
                    }
                )

                return
            }
        }

        // Pull-to-refresh container for the main content.
        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = {
                viewModel.refreshWeather()
            }
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // Search Bar section
                item {
                    SearchBar(
                        city = state.searchCity.trim(),
                        isLoading = state.isLoading && state.weather != null,
                        onCityChanged = viewModel::onCityChanged,
                        onSearch = viewModel::searchWeather
                    )
                }

                // Main weather content section (Current, Hourly, Weekly)
                state.weather?.let { weather ->
                    item {
                        CurrentWeatherCard(weather)
                    }

                    // Check for and display weather-related safety alerts
                    WeatherAlertUtil
                        .getAlert(weather.weatherCondition)
                        ?.let { alert ->

                            item {
                                WeatherAlertCard(
                                    title = androidx.compose.ui.res.stringResource(alert.titleResId),
                                    message = androidx.compose.ui.res.stringResource(alert.messageResId)
                                )
                            }
                        }

                    item {
                        HourlyForecastSection(
                            hourlyForecast = weather.hourlyForecast
                        )
                    }

                    item {
                        Log.d("Weather", weather.weeklyForecast.first().toString())
                        WeeklyForecastSection(
                            weeklyForecast = weather.weeklyForecast
                        )
                    }
                    item {
                        WeatherDetailsSection(weather)
                    }
                }
            }
        }

        // Full-screen loading indicator for initial data fetching.
        if (state.isLoading && state.weather == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Box
        }
    }
}
