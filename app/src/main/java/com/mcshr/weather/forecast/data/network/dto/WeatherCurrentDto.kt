package com.mcshr.weather.forecast.data.network.dto

data class WeatherCurrentDto(
    val weather: List<WeatherDto>,
    val main: MainDto,
    val visibility: Int,
    val wind: WindDto,
    val rain: RainDto?,
    val clouds: CloudsDto?,
    val dt: Long,
    val sys: SysDto,
    val timezone: Int?,
    val name: String,
    )
