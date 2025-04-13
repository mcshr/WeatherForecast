package com.mcshr.weather.forecast.data.network.dto

data class WeatherDto(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
