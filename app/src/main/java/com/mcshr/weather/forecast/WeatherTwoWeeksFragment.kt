package com.mcshr.weather.forecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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