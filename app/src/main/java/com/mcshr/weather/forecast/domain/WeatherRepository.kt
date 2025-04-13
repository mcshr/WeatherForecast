package com.mcshr.weather.forecast.domain

import com.mcshr.weather.forecast.domain.entities.City
import com.mcshr.weather.forecast.domain.entities.WeatherToday

interface WeatherRepository {
    suspend fun getCityByName(cityName:String): City
    suspend fun getWeatherToday(city: City): WeatherToday
    fun saveSelectedCity(city: City)
    fun getSelectedCity():City?
}