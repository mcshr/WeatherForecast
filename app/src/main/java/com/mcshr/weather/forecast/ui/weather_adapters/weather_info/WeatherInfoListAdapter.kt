package com.mcshr.weather.forecast.ui.weather_adapters.weather_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.mcshr.weather.forecast.R
import com.mcshr.weather.forecast.databinding.ItemWeatherInfoBinding
import com.mcshr.weather.forecast.domain.entities.WeatherForecastItem
import org.threeten.bp.format.DateTimeFormatter

class WeatherInfoListAdapter : ListAdapter<WeatherForecastItem, WeatherInfoViewHolder>(
    WeatherInfoDiffCallback()
) {
    override fun onBindViewHolder(holder: WeatherInfoViewHolder, position: Int) {
        val weatherInfo = getItem(position)
        val context = holder.itemView.context
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val time = weatherInfo.timeOfDataCalculation.format(formatter)
        holder.binding.tvTime.text = time
        holder.binding.tvTemp.text = context.getString(
            R.string.temperature,
            weatherInfo.mainData.temp.toInt().toString()
        )
        holder.binding.imageViewWeather.load(weatherInfo.weather.first().iconUrl)
        holder.binding.tvWind.text = context.getString(R.string.wind_short, weatherInfo.wind.speed)
        holder.binding.tvHumidity.text = context.getString(
            R.string.num_with_percent,
            weatherInfo.mainData.humidity.toString()
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherInfoViewHolder {
        val binding = ItemWeatherInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeatherInfoViewHolder(binding)
    }


}