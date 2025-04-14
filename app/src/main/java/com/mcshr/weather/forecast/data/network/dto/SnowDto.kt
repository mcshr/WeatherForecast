package com.mcshr.weather.forecast.data.network.dto

import com.google.gson.annotations.SerializedName

data class SnowDto(
    @SerializedName("3h") val threeHours: Double
)
