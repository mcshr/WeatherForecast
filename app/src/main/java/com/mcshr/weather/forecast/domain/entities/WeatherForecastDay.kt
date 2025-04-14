package com.mcshr.weather.forecast.domain.entities

import org.threeten.bp.LocalDate


data class WeatherForecastDay(
    val day: LocalDate,
    val weatherForecastList: List<WeatherForecastItem>
)