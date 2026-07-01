package com.manoj.weatherinsight.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.manoj.weatherinsight.R
import com.manoj.weatherinsight.data.remote.ForecastDay
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import com.manoj.weatherinsight.core.util.WeatherFormatter

/**
 * Displays a premium weekly weather forecast section.
 *
 * Features:
 * • Single rounded card
 * • Today / Tomorrow / Weekday formatting
 * • Large weather icons
 * • Max / Min temperatures
 * • Divider between rows
 *
 * @author Manojkumar
 */
@Composable
fun WeeklyForecastSection(
    weeklyForecast: List<ForecastDay>
) {
    val context = LocalContext.current
    Text(
        text = stringResource(R.string.weekly_forecast),
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

        Column {

            weeklyForecast.forEachIndexed { index, day ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 16.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Date
                    Text(
                        text = WeatherFormatter.formatDate(day.date),
                        modifier = Modifier.width(70.dp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    // Day
                    Text(
                        text = WeatherFormatter.formatDay(context, day.date),
                        modifier = Modifier.width(70.dp),
                        color = Color.White.copy(alpha = 0.70f),
                        fontSize = 14.sp
                    )

                    // Weather Icon
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data("https:${day.day.condition.icon}")
                            .crossfade(true)
                            .build(),
                        contentDescription = day.day.condition.text,
                        modifier = Modifier.size(40.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.WaterDrop,
                            contentDescription = null,
                            tint = Color(0xFF64B5F6),
                            modifier = Modifier.size(14.dp)
                        )

                        Spacer(modifier = Modifier.width(2.dp))

                        Text(
                            text = stringResource(R.string.rain_chance_value, day.day.dailyChanceOfRain),
                            color = Color(0xFF64B5F6),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    // Temperature
                    Text(
                        text = stringResource(
                            R.string.temp_range_value,
                            day.day.maxtemp_c.toInt(),
                            day.day.mintemp_c.toInt()
                        ),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                if (index != weeklyForecast.lastIndex) {

                    HorizontalDivider(
                        color = Color.White.copy(alpha = 0.08f),
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
            }
        }
    }
}
