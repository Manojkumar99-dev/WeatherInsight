package com.manoj.weatherinsight.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * A small reusable component to display a piece of weather information (e.g., Humidity, Wind Speed)
 * consisting of an icon, a title, and a value.
 *
 * @param icon The [ImageVector] to display.
 * @param title The label for the information (e.g., "Humidity").
 * @param value The actual value string (e.g., "75%").
 * @author Manojkumar
 */
@Composable
fun WeatherInfo(
    icon: ImageVector,
    title: String,
    value: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White
        )

        Text(
            text = title,
            color = Color.White
        )

        Text(
            text = value,
            color = Color.White
        )
    }
}
