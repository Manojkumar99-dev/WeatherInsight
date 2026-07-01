package com.manoj.weatherinsight.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.manoj.weatherinsight.core.util.WeatherFormatter
import com.manoj.weatherinsight.core.util.WeatherTheme
import com.manoj.weatherinsight.data.local.WeatherEntity
import com.manoj.weatherinsight.presentation.components.WeatherInfo
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.remember
import coil.request.ImageRequest

import androidx.compose.ui.res.stringResource
import com.manoj.weatherinsight.R

/**
 * A card displaying the current weather for a city.
 *
 * Features:
 * • Dynamic background based on weather condition.
 * • Large weather icon.
 * • Large temperature display.
 * • Location with country.
 * • Humidity, Wind and Feels Like information.
 * • Last updated time.
 *
 * @param weather Current weather information.
 *
 * @author Manojkumar
 */
@Composable
fun CurrentWeatherCard(
    weather: WeatherEntity
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        )
    ) {
        val backgroundBrush = remember(weather.weatherCondition) {
            WeatherTheme.background(
                context,
                weather.weatherCondition
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundBrush)
                .padding(
                    horizontal = 24.dp,
                    vertical = 28.dp
                )
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(6.dp))
                    val city = remember(weather.city, weather.country) {
                        WeatherFormatter.formatCity(
                            weather.city,
                            weather.country
                        )
                    }

                    Text(
                        text = city,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                val imageRequest = remember(weather.weatherIcon) {
                    ImageRequest.Builder(context)
                        .data(weather.weatherIcon)
                        .crossfade(true)
                        .build()
                }

                AsyncImage(
                    model = imageRequest,
                    contentDescription = weather.weatherCondition,
                    modifier = Modifier.size(110.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))
                val temperature = remember(weather.temperature) {
                    WeatherFormatter.formatTemperatureWithUnit(
                        weather.temperature
                    )
                }

                Text(
                    text = temperature,
                    color = Color.White,
                    fontSize = 68.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = weather.weatherCondition,
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                HorizontalDivider(
                    color = Color.White.copy(alpha = 0.18f)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    WeatherInfo(
                        icon = Icons.Default.WaterDrop,
                        title = stringResource(R.string.humidity),
                        value = stringResource(R.string.humidity_value, weather.humidity)
                    )

                    WeatherInfo(
                        icon = Icons.Default.Air,
                        title = stringResource(R.string.wind),
                        value = stringResource(R.string.wind_value, weather.windSpeed)
                    )

                    WeatherInfo(
                        icon = Icons.Default.Thermostat,
                        title = stringResource(R.string.feels_like),
                        value = stringResource(R.string.temp_value, weather.feelsLike.toInt())
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                val lastUpdated = remember(weather.lastUpdated) {
                    WeatherFormatter.formatLastUpdated(
                        context,
                        weather.lastUpdated
                    )
                }
                Text(
                    text = lastUpdated,
                    color = Color.White.copy(alpha = 0.70f),
                    fontSize = 13.sp
                )
            }
        }
    }
}
