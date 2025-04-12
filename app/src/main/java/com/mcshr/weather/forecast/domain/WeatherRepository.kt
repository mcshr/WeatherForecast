package com.mcshr.weather.forecast.domain

import com.mcshr.weather.forecast.domain.entities.City

interface WeatherRepository {
    suspend fun getCityByName(cityName:String): City
    fun saveSelectedCityName(cityName: String)
    fun getSelectedCityName():String?
}