package com.mcshr.weather.forecast.data

import com.mcshr.weather.forecast.data.network.dto.CityDto
import com.mcshr.weather.forecast.data.network.dto.WeatherCurrentDto
import com.mcshr.weather.forecast.data.network.dto.WeatherDto
import com.mcshr.weather.forecast.domain.entities.City
import com.mcshr.weather.forecast.domain.entities.Weather
import com.mcshr.weather.forecast.domain.entities.WeatherToday


fun CityDto.toDomain(): City {
    return City(
        name = name,
        lat = lat,
        lon = lon,
        country = country
    )
}

fun WeatherCurrentDto.toDomain(): WeatherToday {
    return WeatherToday(
        weather = weather.map { it.toDomain() }
    )
}

fun WeatherDto.toDomain(): Weather {
    return Weather(
        main = main,
        description = description,
        icon = icon
    )
}
