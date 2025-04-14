package com.mcshr.weather.forecast.ui.weather_adapters.weather_info

import androidx.recyclerview.widget.DiffUtil
import com.mcshr.weather.forecast.domain.entities.WeatherForecastItem

class WeatherInfoDiffCallback: DiffUtil.ItemCallback<WeatherForecastItem>() {
    override fun areContentsTheSame(
        oldItem: WeatherForecastItem,
        newItem: WeatherForecastItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(
        oldItem: WeatherForecastItem,
        newItem: WeatherForecastItem
    ): Boolean {
        return oldItem.timeOfDataCalculation == newItem.timeOfDataCalculation
    }

}