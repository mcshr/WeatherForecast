package com.mcshr.weather.forecast.domain

import com.mcshr.weather.forecast.domain.entities.City

interface WeatherRepository {
    fun getCityByName(cityName:String): City
}