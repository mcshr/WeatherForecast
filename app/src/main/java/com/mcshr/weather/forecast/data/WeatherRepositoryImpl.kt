package com.mcshr.weather.forecast.data

import com.google.gson.Gson
import com.mcshr.weather.forecast.data.Mappers.toDomain
import com.mcshr.weather.forecast.data.network.ApiFactory
import com.mcshr.weather.forecast.domain.WeatherRepository
import com.mcshr.weather.forecast.domain.entities.City

class WeatherRepositoryImpl(
    private val sharedPreferences: WeatherSharedPreferences
) : WeatherRepository {
    private val gson = Gson()
    override suspend fun getCityByName(cityName: String): City {
        return ApiFactory.weatherApi.getCityByName(
            cityName = cityName
        ).first().toDomain()
    }

    override fun saveSelectedCity(city: City) {
        val cityJson = gson.toJson(city)
        sharedPreferences.saveSelectedCity(cityJson)
    }

    override fun getSelectedCity(): City? {
        val cityJson = sharedPreferences.getSelectedCity()
        return cityJson?.let{
            try {
                gson.fromJson(it, City::class.java)
            } catch (e:Exception){
                null
            }
        }
    }

}