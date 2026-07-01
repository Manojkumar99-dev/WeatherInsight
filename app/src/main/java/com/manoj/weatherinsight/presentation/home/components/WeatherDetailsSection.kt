package com.manoj.weatherinsight.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manoj.weatherinsight.data.local.WeatherEntity

import androidx.compose.ui.res.stringResource
import com.manoj.weatherinsight.R

/**
 * Displays detailed weather metrics.
 *
 * @author Manojkumar
 */
@Composable
fun WeatherDetailsSection(
    weather: WeatherEntity
) {

    Text(
        text = stringResource(R.string.weather_details),
        style = MaterialTheme.typography.titleLarge,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(12.dp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF16244D)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            WeatherDetailRow(
                Icons.Default.WaterDrop,
                stringResource(R.string.humidity),
                stringResource(R.string.humidity_value, weather.humidity)
            )

            HorizontalDivider(
                color = Color.White.copy(alpha = 0.08f)
            )

            WeatherDetailRow(
                Icons.Default.Air,
                stringResource(R.string.wind),
                stringResource(R.string.wind_value, weather.windSpeed)
            )

            HorizontalDivider(
                color = Color.White.copy(alpha = 0.08f)
            )

            WeatherDetailRow(
                Icons.Default.Thermostat,
                stringResource(R.string.feels_like),
                stringResource(R.string.temp_value_c, weather.feelsLike.toInt())
            )

            HorizontalDivider(
                color = Color.White.copy(alpha = 0.08f)
            )

            WeatherDetailRow(
                Icons.Default.WbSunny,
                stringResource(R.string.uv_index),
                weather.uv.toString()
            )

            HorizontalDivider(
                color = Color.White.copy(alpha = 0.08f)
            )

            WeatherDetailRow(
                Icons.Default.RemoveRedEye,
                stringResource(R.string.visibility),
                stringResource(R.string.visibility_value, weather.visibility)
            )

            HorizontalDivider(
                color = Color.White.copy(alpha = 0.08f)
            )

            WeatherDetailRow(
                Icons.Default.Speed,
                stringResource(R.string.pressure),
                stringResource(R.string.pressure_value, weather.pressure.toInt())
            )
        }
    }
}

@Composable
private fun WeatherDetailRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color(0xFF64B5F6)
        )

        Spacer(modifier = Modifier.width(14.dp))

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = Color.White.copy(alpha = 0.75f),
            fontSize = 16.sp
        )

        Text(
            text = value,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}