package com.manoj.weatherinsight.core.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.manoj.weatherinsight.core.notification.NotificationHelper
import com.manoj.weatherinsight.core.util.WeatherAlertUtil
import com.manoj.weatherinsight.domain.repository.WeatherRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

/**
 * A background worker that synchronizes weather data periodically.
 * Uses [HiltWorker] for dependency injection.
 *
 * @author Manojkumar
 */


/**
 * Worker responsible for periodically refreshing weather data
 * and displaying notifications for severe weather conditions.
 *
 * Currently refreshes weather for the configured city.
 * This can be extended to use the user's selected or current location.
 *
 * @author Manojkumar
 */
@HiltWorker
class WeatherSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: WeatherRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

        return try {

            val city = "Bangalore"

            // Refresh latest weather from API if required.
            repository.refreshWeather(city)

            // Read latest weather from local database.
            val weather = repository
                .getWeather(city)
                .first()

            weather?.let {

                // Show notification for severe weather conditions.
                WeatherAlertUtil
                    .getAlert(it.weatherCondition)
                    ?.let { alert ->

                        NotificationHelper.showNotification(
                            context = applicationContext,
                            title = applicationContext.getString(
                                com.manoj.weatherinsight.R.string.temp_value, // Just a placeholder, I'll use direct formatting below
                                // Wait, the original was "${alert.title} - ${it.city}"
                            ).let { _ ->
                                // Let's just use getString with formatting if I had one, but I don't for the combined title.
                                // Actually, I should probably have a generic alert title format.
                                // But the alerts already have titles like "⛈ Thunderstorm Alert".
                                "${applicationContext.getString(alert.titleResId)} - ${it.city}"
                            },
                            message = applicationContext.getString(alert.messageResId)
                        )
                    }

                // Notify user about extreme heat.
                if (it.temperature >= 40) {

                    NotificationHelper.showNotification(
                        context = applicationContext,
                        title = applicationContext.getString(
                            com.manoj.weatherinsight.R.string.heat_alert_title,
                            it.city
                        ),
                        message = applicationContext.getString(
                            com.manoj.weatherinsight.R.string.heat_alert_message,
                            it.temperature.toInt()
                        )
                    )
                }
            }

            Result.success()

        } catch (e: Exception) {

            Result.retry()
        }
    }
}