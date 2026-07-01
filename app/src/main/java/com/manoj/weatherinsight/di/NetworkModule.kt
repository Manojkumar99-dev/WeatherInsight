package com.manoj.weatherinsight.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.manoj.weatherinsight.core.constants.AppConstants
import com.manoj.weatherinsight.data.remote.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Hilt module that provides network-related dependencies (Retrofit, OkHttp, GSON).
 *
 * @author Manojkumar
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides an HTTP logging interceptor for debugging network requests and responses.
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * Provides a configured [OkHttpClient].
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    /**
     * Provides a [Gson] instance for JSON serialization/deserialization.
     */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    /**
     * Provides a configured [Retrofit] instance.
     */
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    /**
     * Provides the [WeatherApi] instance for making weather-related requests.
     */
    @Provides
    @Singleton
    fun provideWeatherApi(
        retrofit: Retrofit
    ): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }
}
