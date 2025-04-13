package com.mcshr.weather.forecast.data.network

import com.mcshr.weather.forecast.BuildConfig
import com.mcshr.weather.forecast.data.network.dto.CityDto
import com.mcshr.weather.forecast.data.network.dto.WeatherCurrentDto
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("geo/1.0/direct")
    suspend fun getCityByName(
        @Query("q") cityName:String,
        @Query("limit") limit:Int = LIMIT,
        @Query("appid") apiKey:String = BuildConfig.API_KEY
    ): List<CityDto>

    @GET("data/2.5/weather")
    suspend fun getWeatherToday(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("appid") apiKey:String = BuildConfig.API_KEY,
        @Query("units") units:String = TEMPERATURE_IN_CELSIUS
    ):WeatherCurrentDto



    @GET("img/wn/10d@2x.png")
    suspend fun getIconByCode()


    companion object{
        private const val LIMIT = 1
        private const val TEMPERATURE_IN_FAHRENHEIT = "imperial"
        private const val TEMPERATURE_IN_CELSIUS = "metric"

    }
}