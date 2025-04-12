package com.mcshr.weather.forecast.data.network

import com.mcshr.weather.forecast.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("geo/1.0/direct")
    suspend fun getCityByName(
        @Query("q") cityName:String,
        @Query("limit") limit:Int = LIMIT,
        @Query("appid") APIkey:String = BuildConfig.API_KEY
    ): List<CityDto>

    @GET("data/2.5/forecast/hourly")
    suspend fun getWeatherToday()

    @GET("img/wn/10d@2x.png")
    suspend fun getIconByCode()


    companion object{
        private const val LIMIT = 1
    }
}