package com.mcshr.weather.forecast.ui.screens.weather_today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mcshr.weather.forecast.databinding.FragmentWeatherTodayBinding


class WeatherTodayFragment : Fragment() {

    private var _binding: FragmentWeatherTodayBinding? = null
    private val binding
        get() = _binding ?:throw RuntimeException(" Fragment WeatherToday binding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() =
            WeatherTodayFragment()
    }
}