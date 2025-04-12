package com.mcshr.weather.forecast.data

import com.mcshr.weather.forecast.data.Mappers.toDomain
import com.mcshr.weather.forecast.data.network.ApiFactory
import com.mcshr.weather.forecast.domain.WeatherRepository
import com.mcshr.weather.forecast.domain.entities.City

class WeatherRepositoryImpl : WeatherRepository {


    override suspend fun getCityByName(cityName: String): City {
        return ApiFactory.weatherApi.getCityByName(
            cityName = cityName, APIkey = API_KEY
        ).first().toDomain()
    }

    companion object {
        private const val API_KEY = "962c846fda8a2a0dce8dd731f39dcb29"
    }
}