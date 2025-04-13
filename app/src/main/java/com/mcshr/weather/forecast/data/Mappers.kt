package com.mcshr.weather.forecast.data

import com.mcshr.weather.forecast.data.network.dto.CityDto
import com.mcshr.weather.forecast.data.network.dto.MainDto
import com.mcshr.weather.forecast.data.network.dto.RainDto
import com.mcshr.weather.forecast.data.network.dto.WeatherCurrentDto
import com.mcshr.weather.forecast.data.network.dto.WeatherDto
import com.mcshr.weather.forecast.data.network.dto.WindDto
import com.mcshr.weather.forecast.domain.entities.City
import com.mcshr.weather.forecast.domain.entities.Rain
import com.mcshr.weather.forecast.domain.entities.Weather
import com.mcshr.weather.forecast.domain.entities.WeatherMainData
import com.mcshr.weather.forecast.domain.entities.WeatherToday
import com.mcshr.weather.forecast.domain.entities.Wind
import com.mcshr.weather.forecast.ui.utils.getImageUrl


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
        weather = weather.map { it.toDomain() },
        mainData = main.toDomain(),
        visibility = visibility,
        wind = wind.toDomain(),
        rain = rain?.toDomain(),
        clouds = clouds.all,
        timeOfDataCalculation = dt,
        timezone =  timezone
    )
}

fun WeatherDto.toDomain(): Weather {
    return Weather(
        main = main,
        description = description,
        iconUrl = getImageUrl(icon)
    )
}

fun MainDto.toDomain():WeatherMainData{
    return WeatherMainData(
        temp =temp,
        feelsLike = feels_like,
        pressure = pressure,
        humidity = humidity,
        seaLevel = sea_level,
        grndLevel = grnd_level
    )
}
fun WindDto.toDomain():Wind{
    return Wind(speed, deg, gust)
}
fun RainDto.toDomain(): Rain {
    return Rain(
        oneHour = oneHour,
        threeHours = threeHours
    )
}
