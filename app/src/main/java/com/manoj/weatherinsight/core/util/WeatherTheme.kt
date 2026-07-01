package com.manoj.weatherinsight.core.util

import android.content.Context
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.manoj.weatherinsight.R

/**
 * Provides dynamic weather-based gradients used throughout the application.
 *
 * - background()      -> Hero weather card
 * - screenBackground() -> Entire screen background
 *
 * @author Manojkumar
 */
object WeatherTheme {

    /**
     * Returns a premium gradient for the Current Weather card.
     */
    fun background(
        context: Context,
        condition: String
    ): Brush {

        return when {

            // ☀️ Sunny / Clear
            condition.contains(context.getString(R.string.cond_sun), true) ||
                    condition.contains(context.getString(R.string.cond_clear), true) -> {

                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFF8F00),
                        Color(0xFFFFC107),
                        Color(0xFFFFE082)
                    ),
                    start = Offset.Zero,
                    end = Offset(1200f, 1200f)
                )
            }

            // ☁️ Cloudy
            condition.contains(context.getString(R.string.cond_cloud), true) -> {

                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF546E7A),
                        Color(0xFF78909C),
                        Color(0xFFB0BEC5)
                    ),
                    start = Offset.Zero,
                    end = Offset(1200f, 1200f)
                )
            }

            // 🌧 Rain / Drizzle
            condition.contains(context.getString(R.string.cond_rain), true) ||
                    condition.contains(context.getString(R.string.cond_drizzle), true) -> {

                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0D47A1),
                        Color(0xFF1976D2),
                        Color(0xFF64B5F6)
                    ),
                    start = Offset.Zero,
                    end = Offset(1200f, 1200f)
                )
            }

            // ⛈ Thunderstorm
            condition.contains(context.getString(R.string.cond_storm), true) ||
                    condition.contains(context.getString(R.string.cond_thunder), true) -> {

                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF311B92),
                        Color(0xFF5E35B1),
                        Color(0xFF9575CD)
                    ),
                    start = Offset.Zero,
                    end = Offset(1200f, 1200f)
                )
            }

            // ❄️ Snow
            condition.contains(context.getString(R.string.cond_snow), true) -> {

                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFE3F2FD),
                        Color(0xFFF5F9FF),
                        Color.White
                    ),
                    start = Offset.Zero,
                    end = Offset(1200f, 1200f)
                )
            }

            // 🌫 Mist / Fog
            condition.contains(context.getString(R.string.cond_mist), true) ||
                    condition.contains(context.getString(R.string.cond_fog), true) -> {

                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF78909C),
                        Color(0xFFB0BEC5),
                        Color(0xFFECEFF1)
                    ),
                    start = Offset.Zero,
                    end = Offset(1200f, 1200f)
                )
            }

            // Default
            else -> {

                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1565C0),
                        Color(0xFF42A5F5),
                        Color(0xFF81D4FA)
                    ),
                    start = Offset.Zero,
                    end = Offset(1200f, 1200f)
                )
            }
        }
    }

    /**
     * Returns a subtle gradient for the entire application background.
     */
    fun screenBackground(
        context: Context,
        condition: String
    ): Brush {

        return when {

            condition.contains(context.getString(R.string.cond_sun), true) ||
                    condition.contains(context.getString(R.string.cond_clear), true) -> {

                Brush.verticalGradient(
                    listOf(
                        Color(0xFF0B1F4D),
                        Color(0xFF294D8E)
                    )
                )
            }

            condition.contains(context.getString(R.string.cond_cloud), true) -> {

                Brush.verticalGradient(
                    listOf(
                        Color(0xFF09162E),
                        Color(0xFF34495E)
                    )
                )
            }

            condition.contains(context.getString(R.string.cond_rain), true) ||
                    condition.contains(context.getString(R.string.cond_drizzle), true) -> {

                Brush.verticalGradient(
                    listOf(
                        Color(0xFF081A3A),
                        Color(0xFF123E73)
                    )
                )
            }

            condition.contains(context.getString(R.string.cond_storm), true) ||
                    condition.contains(context.getString(R.string.cond_thunder), true) -> {

                Brush.verticalGradient(
                    listOf(
                        Color(0xFF120A2A),
                        Color(0xFF36275D)
                    )
                )
            }

            condition.contains(context.getString(R.string.cond_snow), true) -> {

                Brush.verticalGradient(
                    listOf(
                        Color(0xFFBFD8F5),
                        Color(0xFFEAF4FF)
                    )
                )
            }

            else -> {

                Brush.verticalGradient(
                    listOf(
                        Color(0xFF071238),
                        Color(0xFF10204A)
                    )
                )
            }
        }
    }
}