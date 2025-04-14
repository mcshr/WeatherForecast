package com.mcshr.weather.forecast.data.network.dto

data class WeatherForecast5daysDto(
    val cnt: Int,
    val list: List<WeatherForecastDto>
)
