package com.mcshr.weather.forecast.data.network.dto

import com.google.gson.annotations.SerializedName

data class RainDto(
    @SerializedName("1h") val oneHour: Double,
)
