package com.mcshr.weather.forecast.domain.interactors

import com.mcshr.weather.forecast.domain.WeatherRepository
import com.mcshr.weather.forecast.domain.entities.City
import javax.inject.Inject

class SaveSelectedCityUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(city: City){
        repository.saveSelectedCity(city)
    }
}