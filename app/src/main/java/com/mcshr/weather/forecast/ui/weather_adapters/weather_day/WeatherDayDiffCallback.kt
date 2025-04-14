package com.mcshr.weather.forecast.ui.weather_adapters.weather_day

import androidx.recyclerview.widget.DiffUtil
import com.mcshr.weather.forecast.domain.entities.WeatherForecastDay

class WeatherDayDiffCallback : DiffUtil.ItemCallback<WeatherForecastDay>() {
    override fun areContentsTheSame(
        oldItem: WeatherForecastDay, newItem: WeatherForecastDay
    ): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(
        oldItem: WeatherForecastDay, newItem: WeatherForecastDay
    ): Boolean {
        return oldItem.weatherForecastList.first().timeOfDataCalculation == newItem.weatherForecastList.first().timeOfDataCalculation
    }
}