package com.manoj.weatherinsight

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.manoj.weatherinsight.core.worker.WorkManagerHelper
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

/**
 * Base Application class for the project.
 * [HiltAndroidApp] triggers Hilt's code generation, including a base class for your application
 * that serves as the application-level dependency container.
 * Implements [Configuration.Provider] to provide a custom [Configuration] for WorkManager,
 * specifically to enable Hilt injection into Workers.
 *
 * @author Manojkumar
 */
@HiltAndroidApp
class WeatherInsightApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    /**
     * Custom WorkManager configuration to support Hilt dependency injection in Workers.
     */
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()

        // Initialize Timber for debug logging.
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        
        // Schedule periodic weather synchronization tasks.
        WorkManagerHelper.scheduleWeatherSync(this)
    }
}
