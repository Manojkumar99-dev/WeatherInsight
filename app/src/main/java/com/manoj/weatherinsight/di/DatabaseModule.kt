package com.manoj.weatherinsight.di

import android.content.Context
import androidx.room.Room
import com.manoj.weatherinsight.core.constants.AppConstants
import com.manoj.weatherinsight.data.local.WeatherDao
import com.manoj.weatherinsight.data.local.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides database-related dependencies.
 *
 * @author Manojkumar
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides a singleton instance of [WeatherDatabase].
     */
    @Provides
    @Singleton
    fun provideWeatherDatabase(
        @ApplicationContext context: Context
    ): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            AppConstants.DATABASE_NAME
        )
        .fallbackToDestructiveMigration() // Allows migrations to simply recreate the DB if schema changes.
        .build()
    }

    /**
     * Provides the [WeatherDao] for performing database operations.
     */
    @Provides
    @Singleton
    fun provideWeatherDao(
        database: WeatherDatabase
    ): WeatherDao {
        return database.weatherDao()
    }
}
