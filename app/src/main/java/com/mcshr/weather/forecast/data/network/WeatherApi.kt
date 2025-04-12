package com.mcshr.weather.forecast.data.network

import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("geo/1.0/direct")
    suspend fun getCityByName(
        @Query("q") cityName:String,
        @Query("limit") limit:Int = LIMIT,
        @Query("appid") APIkey:String
    ): List<CityDto>



    companion object{
        private const val LIMIT = 1
    }
}