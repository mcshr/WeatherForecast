package com.mcshr.weather.forecast.data

import com.mcshr.weather.forecast.data.network.dto.CityDto
import com.mcshr.weather.forecast.data.network.dto.MainDto
import com.mcshr.weather.forecast.data.network.dto.RainDto
import com.mcshr.weather.forecast.data.network.dto.WeatherDto
import com.mcshr.weather.forecast.data.network.dto.WeatherForecast5daysDto
import com.mcshr.weather.forecast.data.network.dto.WeatherForecastDto
import com.mcshr.weather.forecast.data.network.dto.WindDto
import com.mcshr.weather.forecast.data.network.getImageUrl
import com.mcshr.weather.forecast.domain.entities.City
import com.mcshr.weather.forecast.domain.entities.Rain
import com.mcshr.weather.forecast.domain.entities.Weather
import com.mcshr.weather.forecast.domain.entities.WeatherForecastDay
import com.mcshr.weather.forecast.domain.entities.WeatherForecastItem
import com.mcshr.weather.forecast.domain.entities.WeatherMainData
import com.mcshr.weather.forecast.domain.entities.Wind
import org.threeten.bp.Instant
import org.threeten.bp.ZoneOffset


fun CityDto.toDomain(): City {
    return City(
        name = name,
        lat = lat,
        lon = lon,
        country = country
    )
}

fun WeatherForecast5daysDto.toDomain(): List<WeatherForecastDay>{
    return  list.map{
        it.toDomain()
    }.groupBy {
       it.timeOfDataCalculation.toLocalDate()
    }.map {
        WeatherForecastDay(it.key, it.value)
    }
}

fun WeatherForecastDto.toDomain(): WeatherForecastItem {
    val zonedDateTime = Instant.ofEpochSecond(dt)
        .atZone(ZoneOffset.ofTotalSeconds(timezone.toInt()))
    return WeatherForecastItem(
        weather = weather.map { it.toDomain() },
        mainData = main.toDomain(),
        visibility = visibility,
        wind = wind.toDomain(),
        rain = rain?.toDomain(),
        clouds = clouds.all,
        timeOfDataCalculation = zonedDateTime
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
