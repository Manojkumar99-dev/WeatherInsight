package com.manoj.weatherinsight.core.util

import android.content.Context
import com.manoj.weatherinsight.R
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import kotlin.math.roundToInt

/**
 * Utility object for formatting weather-related data into human-readable strings.
 *
 * @author Manojkumar
 */
object WeatherFormatter {

    /**
     * Formats an API hour string into a user-friendly time.
     *
     * Example:
     * 2026-07-01 02:00 -> 2:00 AM
     * 2026-07-01 13:00 -> 1:00 PM
     */
    fun formatHour(time: String): String {

        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        val outputFormatter = DateTimeFormatter.ofPattern("h:mm a")

        return LocalDateTime
            .parse(time, inputFormatter)
            .format(outputFormatter)
    }

    /**
     * Formats temperature by rounding to the nearest integer and adding a degree symbol.
     * 
     * @param temp The temperature in Double.
     * @return Formatted temperature string (e.g., "25°").
     */
    fun formatTemperature(temp: Double): String {
        return "${temp.roundToInt()}°"
    }

    /**
     * Formats temperature by rounding to the nearest integer and adding "°C".
     * 
     * @param temp The temperature in Double.
     * @return Formatted temperature string with unit (e.g., "25°C").
     */
    fun formatTemperatureWithUnit(temp: Double): String {
        return "${temp.roundToInt()}°C"
    }

    /**
     * Formats city and country into a "City, Country" string.
     */
    fun formatCity(city: String, country: String): String {
        return "$city, $country"
    }

    fun formatLastUpdated(context: Context, lastUpdated: Long): String {

        val dateTime = Instant.ofEpochMilli(lastUpdated)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()

        val today = LocalDate.now()
        val yesterday = today.minusDays(1)

        val time = dateTime.format(
            DateTimeFormatter.ofPattern("h:mm a")
        )

        return when (dateTime.toLocalDate()) {

            today ->
                context.getString(R.string.updated_today, time)

            yesterday ->
                context.getString(R.string.updated_yesterday, time)

            else ->
                context.getString(
                    R.string.updated_at,
                    dateTime.format(DateTimeFormatter.ofPattern("d MMM")),
                    time
                )
        }
    }

    fun formatDate(date: String): String {

        val input = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val output = DateTimeFormatter.ofPattern("d MMM")

        return LocalDate
            .parse(date, input)
            .format(output)
    }

    fun formatDay(context: Context, date: String): String {

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val parsed = LocalDate.parse(date, formatter)

        return when (parsed) {

            LocalDate.now() -> context.getString(R.string.today)

            LocalDate.now().plusDays(1) -> context.getString(R.string.tomorrow)

            else -> parsed.dayOfWeek.getDisplayName(
                TextStyle.SHORT,
                Locale.getDefault()
            )
        }
    }
}