package com.mcshr.weather.forecast.domain.entities

data class Weather(
    val main: String,
    val description: String,
    val iconUrl: String
)
