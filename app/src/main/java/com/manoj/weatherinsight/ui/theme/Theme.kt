package com.manoj.weatherinsight.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

/**
 * Dark color scheme definition for Material 3.
 */
private val DarkColorScheme = darkColorScheme(

    primary = Color(0xFF42A5F5),

    secondary = Color(0xFF64B5F6),

    tertiary = Color(0xFF90CAF9),

    background = Color(0xFF071238),

    surface = Color(0xFF10204A)
)

/**
 * Light color scheme definition for Material 3.
 */
private val LightColorScheme = lightColorScheme(

    primary = Color(0xFF1565C0),

    secondary = Color(0xFF1976D2),

    tertiary = Color(0xFF42A5F5),

    background = Color(0xFFF3F8FF),

    surface = Color.White
)

/**
 * The main theme composable for the Weather Insight application.
 * Manages color schemes (Light, Dark, and Dynamic) and typography.
 *
 * @param darkTheme Whether the app is in dark mode (defaults to system setting).
 * @param dynamicColor Whether to use dynamic color (Android 12+).
 * @param content The composable content to apply the theme to.
 * @author Manojkumar
 */
@Composable
fun WeatherInsightTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // Use dynamic color on Android 12+ (S)
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography
    ) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent
        ) {
            content()
        }
    }
}
