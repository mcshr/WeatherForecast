package com.mcshr.weather.forecast.data

import com.mcshr.weather.forecast.data.Mappers.toDomain
import com.mcshr.weather.forecast.data.network.ApiFactory
import com.mcshr.weather.forecast.domain.WeatherRepository
import com.mcshr.weather.forecast.domain.entities.City

class WeatherRepositoryImpl(private val sharedPreferences: WeatherSharedPreferences) :
    WeatherRepository {

    override suspend fun getCityByName(cityName: String): City {
        return ApiFactory.weatherApi.getCityByName(
            cityName = cityName, APIkey = API_KEY
        ).first().toDomain()
    }

    override fun saveSelectedCityName(cityName: String) {
        sharedPreferences.saveSelectedCity(cityName)
    }

    override fun getSelectedCityName(): String? {
        return sharedPreferences.getSelectedCity()
    }

    companion object {
        private const val API_KEY = "962c846fda8a2a0dce8dd731f39dcb29"
    }
}