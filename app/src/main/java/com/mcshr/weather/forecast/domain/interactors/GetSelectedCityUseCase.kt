package com.mcshr.weather.forecast.domain.interactors

import com.mcshr.weather.forecast.domain.WeatherRepository
import com.mcshr.weather.forecast.domain.entities.City
import javax.inject.Inject

class GetSelectedCityUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(): City?{
        return repository.getSelectedCity()
    }
}