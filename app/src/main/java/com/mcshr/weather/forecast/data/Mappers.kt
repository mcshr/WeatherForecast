package com.mcshr.weather.forecast.data

import com.mcshr.weather.forecast.data.network.CityDto
import com.mcshr.weather.forecast.domain.entities.City

object Mappers {
    fun CityDto.toDomain(): City {
        return City(
            name = name,
            lat =lat,
            lon = lon,
            country = country
        )
    }
}