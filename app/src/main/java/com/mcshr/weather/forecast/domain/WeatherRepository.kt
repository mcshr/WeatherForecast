package com.mcshr.weather.forecast.domain

import com.mcshr.weather.forecast.domain.entities.City
import com.mcshr.weather.forecast.domain.entities.WeatherForecastDay
import com.mcshr.weather.forecast.domain.entities.WeatherForecastItem

interface WeatherRepository {
    suspend fun getCityByName(cityName:String): City
    suspend fun getWeatherToday(city: City): WeatherForecastItem
    suspend fun getWeatherWeek(city: City): List<WeatherForecastDay>
    fun saveSelectedCity(city: City)
    fun getSelectedCity():City?
}