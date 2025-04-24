package com.mcshr.weather.forecast.domain.interactors

import com.mcshr.weather.forecast.domain.WeatherRepository
import com.mcshr.weather.forecast.domain.entities.City
import com.mcshr.weather.forecast.domain.entities.WeatherForecastItem
import javax.inject.Inject

class GetWeatherCurrentUseCase
@Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(city: City): WeatherForecastItem {
        return repository.getWeatherToday(city)
    }
}