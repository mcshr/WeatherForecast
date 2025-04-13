package com.mcshr.weather.forecast.domain.entities

data class WeatherMainData(
    val temp: Double,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val seaLevel: Int,
    val grndLevel: Int
)
