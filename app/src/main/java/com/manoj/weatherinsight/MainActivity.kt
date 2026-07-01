package com.manoj.weatherinsight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.manoj.weatherinsight.presentation.navigation.NavGraph
import com.manoj.weatherinsight.ui.theme.WeatherInsightTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.manoj.weatherinsight.core.permission.PermissionManager
import android.Manifest
import android.os.Build

/**
 * The main activity of the application.
 * This class serves as the entry point and hosts the [NavGraph] within a Compose environment.
 * Hilt is used for dependency injection via [AndroidEntryPoint].
 *
 * @author Manojkumar
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val notificationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                scrim = android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.dark(
                scrim = android.graphics.Color.TRANSPARENT
            )
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            !PermissionManager.hasNotificationPermission(this)
        ) {

            notificationPermissionLauncher.launch(
                Manifest.permission.POST_NOTIFICATIONS
            )
        }

        setContent {

            WeatherInsightTheme {

                val navController = rememberNavController()

                val snackbarHostState = remember {
                    SnackbarHostState()
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFF071238),
                    snackbarHost = {
                        SnackbarHost(snackbarHostState)
                    }
                ) { innerPadding ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {

                        NavGraph(
                            navController = navController,
                            snackbarHostState = snackbarHostState
                        )
                    }
                }
            }
        }
    }
}