package com.manoj.weatherinsight.core.util

import com.manoj.weatherinsight.R

/**
 * Utility class for generating weather alert notifications.
 *
 * @author Manojkumar
 */
object WeatherAlertUtil {

    data class WeatherAlert(
        val titleResId: Int,
        val messageResId: Int
    )

    /**
     * Returns a weather alert for the given weather condition.
     *
     * @param condition Current weather condition.
     * @return [WeatherAlert] if applicable, otherwise null.
     */
    fun getAlert(condition: String): WeatherAlert? {

        return when {

            condition.contains("Thunder", true) ||
                    condition.contains("Storm", true) ->

                WeatherAlert(
                    titleResId = R.string.alert_thunderstorm_title,
                    messageResId = R.string.alert_thunderstorm_message
                )

            condition.contains("Heavy Rain", true) ->

                WeatherAlert(
                    titleResId = R.string.alert_heavy_rain_title,
                    messageResId = R.string.alert_heavy_rain_message
                )

            condition.contains("Light Rain", true) ||
                    condition.contains("Moderate Rain", true) ||
                    condition.contains("Rain", true) ||
                    condition.contains("Drizzle", true) ->

                WeatherAlert(
                    titleResId = R.string.alert_rain_title,
                    messageResId = R.string.alert_rain_message
                )

            condition.contains("Cyclone", true) ->

                WeatherAlert(
                    titleResId = R.string.alert_cyclone_title,
                    messageResId = R.string.alert_cyclone_message
                )

            condition.contains("Snow", true) ->

                WeatherAlert(
                    titleResId = R.string.alert_snow_title,
                    messageResId = R.string.alert_snow_message
                )

            else -> null
        }
    }
}