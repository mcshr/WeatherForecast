package com.mcshr.weather.forecast.domain.interactors

import com.mcshr.weather.forecast.domain.WeatherRepository
import com.mcshr.weather.forecast.domain.entities.City
import com.mcshr.weather.forecast.domain.entities.WeatherForecastDay
import javax.inject.Inject

class GetWeatherWeekUseCase
@Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(city: City): List<WeatherForecastDay> {
        return repository.getWeatherWeek(city)
    }
}