package com.mcshr.weather.forecast.ui.screens.weather_today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mcshr.weather.forecast.databinding.FragmentWeatherTodayBinding
import com.mcshr.weather.forecast.ui.utils.showMessage


class WeatherTodayFragment : Fragment() {

    private var _binding: FragmentWeatherTodayBinding? = null
    private val binding
        get() = _binding ?:throw RuntimeException(" Fragment WeatherToday binding is null")

    private val viewModel by viewModels<WeatherTodayViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.selectedCity?.let {
            binding.toolbar.subtitle = "${it.name}, ${it.country}"
        }
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        viewModel.getWeather()
        viewModel.validationMessage.observe(viewLifecycleOwner){
            context?.showMessage(it)
        }

        
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() =
            WeatherTodayFragment()
    }
}