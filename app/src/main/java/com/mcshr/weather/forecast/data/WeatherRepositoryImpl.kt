package com.mcshr.weather.forecast.data

import com.mcshr.weather.forecast.domain.WeatherRepository
import com.mcshr.weather.forecast.domain.entities.City

class WeatherRepositoryImpl:WeatherRepository {
    override fun getCityByName(cityName: String): City {
        TODO("Not yet implemented")
    }
}