package com.manoj.weatherinsight.core.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

/**
 * Helper object to manage and schedule WorkManager tasks.
 *
 * @author Manojkumar
 */
object WorkManagerHelper {

    private const val WEATHER_SYNC = "weather_sync"

    /**
     * Schedules a periodic weather synchronization task that runs every hour.
     * Uses [ExistingPeriodicWorkPolicy.KEEP] to avoid resetting the schedule if it already exists.
     * 
     * @param context Android [Context] to access [WorkManager].
     */
    fun scheduleWeatherSync(context: Context) {
        // Create a periodic work request with a 1-hour interval.
        val request =
            PeriodicWorkRequestBuilder<WeatherSyncWorker>(
                1,
                TimeUnit.HOURS
            ).build()

        // Enqueue the unique periodic work.
        WorkManager
            .getInstance(context)
            .enqueueUniquePeriodicWork(
                WEATHER_SYNC,
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
    }
}