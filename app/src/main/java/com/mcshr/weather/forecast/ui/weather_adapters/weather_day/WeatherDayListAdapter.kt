package com.mcshr.weather.forecast.ui.weather_adapters.weather_day

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mcshr.weather.forecast.databinding.ItemWeatherDayBinding
import com.mcshr.weather.forecast.domain.entities.WeatherForecastDay
import com.mcshr.weather.forecast.ui.weather_adapters.weather_info.WeatherInfoListAdapter
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale


class WeatherDayListAdapter :
    ListAdapter<WeatherForecastDay, WeatherDayViewHolder>(WeatherDayDiffCallback()) {
    override fun onBindViewHolder(holder: WeatherDayViewHolder, position: Int) {
        val weatherDay = getItem(position)
        val formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM", Locale.ENGLISH)
        val date = weatherDay.day.format(formatter)
        holder.binding.tvDate.text = date
        val adapter = WeatherInfoListAdapter()
        adapter.submitList(weatherDay.weatherForecastList)
        holder.binding.rvWeatherList.adapter = adapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDayViewHolder {
        val binding = ItemWeatherDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeatherDayViewHolder(binding)
    }
}