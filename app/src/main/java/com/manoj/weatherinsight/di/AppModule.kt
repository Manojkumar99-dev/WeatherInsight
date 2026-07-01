package com.manoj.weatherinsight.di

import com.manoj.weatherinsight.data.repository.WeatherRepositoryImpl
import com.manoj.weatherinsight.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides singleton bindings for application-wide interfaces.
 *
 * @author Manojkumar
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    /**
     * Binds the [WeatherRepository] interface to its implementation [WeatherRepositoryImpl].
     */
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        repository: WeatherRepositoryImpl
    ): WeatherRepository
}
