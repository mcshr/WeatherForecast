package com.mcshr.weather.forecast.ui.screens.weather_2weeks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mcshr.weather.forecast.databinding.FragmentWeatherTwoWeeksBinding


class WeatherTwoWeeksFragment : Fragment() {

    private var _binding: FragmentWeatherTwoWeeksBinding? = null
    private val binding
        get() = _binding ?:throw RuntimeException("Fragment WeatherTwoWeeks binding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherTwoWeeksBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() =
            WeatherTwoWeeksFragment()
    }
}