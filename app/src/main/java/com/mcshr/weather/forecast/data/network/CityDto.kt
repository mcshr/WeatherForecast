package com.mcshr.weather.forecast.data.network

data class CityDto(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String
)
