package com.mcshr.weather.forecast.data.network.dto

data class WeatherForecastDto(
    val weather: List<WeatherDto>,
    val main: MainDto,
    val visibility: Int,
    val wind: WindDto,
    val rain: RainDto?,
    val clouds: CloudsDto,
    val dt: Long,
    val sys: SysDto,
    val timezone: Long,
    val name: String,

    val pop: Double,
    val snow: SnowDto?,
    val sunrise: Long,
    val sunset: Long
    )
