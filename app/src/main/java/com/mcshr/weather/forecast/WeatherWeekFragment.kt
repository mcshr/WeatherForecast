package com.mcshr.weather.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mcshr.weather.forecast.databinding.FragmentWeatherWeekBinding


class WeatherWeekFragment : Fragment() {

    private var _binding: FragmentWeatherWeekBinding? = null
    private val binding
        get() = _binding ?:throw RuntimeException("Fragment WeatherWeek binding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherWeekBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() =
            WeatherWeekFragment()
    }
}