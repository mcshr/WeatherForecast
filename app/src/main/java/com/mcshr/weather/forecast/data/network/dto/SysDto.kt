package com.mcshr.weather.forecast.data.network.dto

data class SysDto(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long,
    val pod: String?
)