package com.mcshr.weather.forecast.domain.entities

import org.threeten.bp.ZonedDateTime


data class WeatherForecastItem(
    val weather: List<Weather>,
    val mainData: WeatherMainData,
    val visibility: Int,
    val wind: Wind,
    val rain: Rain?,
    val clouds: Int,
    val timeOfDataCalculation: ZonedDateTime
)
