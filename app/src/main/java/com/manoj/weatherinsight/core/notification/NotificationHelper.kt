package com.manoj.weatherinsight.core.notification



import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.manoj.weatherinsight.R

/**
 * Helper class responsible for displaying weather notifications.
 *
 * Creates the notification channel (Android 8.0+) and shows
 * high-priority notifications for severe weather alerts.
 *
 * @author Manojkumar
 */
object NotificationHelper {

    private const val CHANNEL_ID = "weather_alert_channel"
    private const val NOTIFICATION_ID = 1001

    /**
     * Displays a weather notification.
     *
     * @param context Application context.
     * @param title Notification title.
     * @param message Notification message.
     */
    fun showNotification(
        context: Context,
        title: String,
        message: String
    ) {

        val notificationManager =
            context.getSystemService(NotificationManager::class.java)

        // Create notification channel for Android O and above.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.channel_weather_alerts),
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(
            context,
            CHANNEL_ID
        )
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            NOTIFICATION_ID,
            notification
        )
    }
}