package com.mcshr.weather.forecast.di

import android.content.Context
import com.mcshr.weather.forecast.data.WeatherRepositoryImpl
import com.mcshr.weather.forecast.data.WeatherSharedPreferences
import com.mcshr.weather.forecast.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideSharedPrefs(@ApplicationContext context: Context): WeatherSharedPreferences {
        return WeatherSharedPreferences(context)
    }

    @Provides
    fun provideWeatherRepo(prefs: WeatherSharedPreferences): WeatherRepository {
        return WeatherRepositoryImpl(prefs)
    }

}