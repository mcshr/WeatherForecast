package com.mcshr.weather.forecast.domain.interactors

import com.mcshr.weather.forecast.domain.WeatherRepository
import com.mcshr.weather.forecast.domain.entities.City
import javax.inject.Inject

class GetCityByNameUseCase @Inject constructor(
    private val repository: WeatherRepository
){
    suspend operator fun invoke(cityName:String): City {
        return repository.getCityByName(cityName)
    }
}