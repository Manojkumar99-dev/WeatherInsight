package com.manoj.weatherinsight.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.manoj.weatherinsight.R
import com.manoj.weatherinsight.core.util.WeatherFormatter
import com.manoj.weatherinsight.data.remote.Hour
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember

/**
 * Displays the next few hours weather forecast in a horizontally
 * scrollable premium card.
 *
 * @author Manojkumar
 */@Composable
fun HourlyForecastSection(
    hourlyForecast: List<Hour>
) {
    val context = LocalContext.current

    val formatter = remember {
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    }

    val currentHour = remember {
        LocalDateTime.now().hour
    }

    val upcomingHours = remember(hourlyForecast) {

        val startIndex = hourlyForecast.indexOfFirst { hour ->

            val forecastHour = LocalDateTime
                .parse(hour.time, formatter)
                .hour

            forecastHour >= currentHour
        }

        if (startIndex != -1) {
            hourlyForecast.drop(startIndex).take(8)
        } else {
            hourlyForecast.takeLast(8)
        }
    }

    Text(
        text = stringResource(R.string.hourly_forecast),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = Color.White
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

        LazyRow(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(
                start = 4.dp,
                end = 4.dp,
                top = 20.dp,
                bottom = 20.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            itemsIndexed(
                items = upcomingHours,
                key = { _, hour -> hour.time }
            ) { index, hour ->

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = if (index == 0)
                            stringResource(R.string.now)
                        else
                            WeatherFormatter.formatHour(hour.time),
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data("https:${hour.condition.icon}")
                            .crossfade(true)
                            .build(),
                        contentDescription = hour.condition.text,
                        modifier = Modifier.size(48.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = WeatherFormatter.formatTemperature(hour.temp_c),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}