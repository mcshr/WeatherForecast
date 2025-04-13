package com.mcshr.weather.forecast.domain.entities

data class WeatherToday(
    val weather: List<Weather>,
    val mainData: WeatherMainData,
    val visibility: Int,
    val wind: Wind,
    val rain: Rain?,
    val clouds: Int,
    val timeOfDataCalculation: Long,
    val timezone: Long
)
