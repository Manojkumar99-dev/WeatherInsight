package com.manoj.weatherinsight.core.constants



/**
 * Global constants used throughout the application.
 *
 * @author Manojkumar
 */
object AppConstants {

    /** Base URL for the Weather API service. */
    const val BASE_URL = "https://api.weatherapi.com/v1/"

    /** Number of forecast days to retrieve. */
    const val FORECAST_DAYS = 7

    /** Cache Time-To-Live in milliseconds (30 minutes). */
    const val CACHE_TTL_MS = 30 * 60 * 1000L

    /** Name of the Room database file. */
    const val DATABASE_NAME = "weather_database"

    /** Unique tag for the weather synchronization background work. */
    const val WEATHER_SYNC_WORK = "weather_sync_work"

    /** ID for weather alert notification channel. */
    const val WEATHER_CHANNEL_ID = "weather_alert_channel"
}
