package com.manoj.weatherinsight.core.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

/**
 * Utility class for checking runtime permissions.
 *
 * @author Manojkumar
 */
object PermissionManager {

    /**
     * Returns true if notification permission is already granted.
     */
    fun hasNotificationPermission(
        context: Context
    ): Boolean {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

        } else {

            true
        }
    }
}