package com.mcshr.weather.forecast.ui.screens.weather_week

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mcshr.weather.forecast.databinding.FragmentWeatherWeekBinding
import com.mcshr.weather.forecast.ui.utils.showMessage
import com.mcshr.weather.forecast.ui.weather_adapters.weather_day.WeatherDayListAdapter


class WeatherWeekFragment : Fragment() {

    private var _binding: FragmentWeatherWeekBinding? = null
    private val binding
        get() = _binding ?:throw RuntimeException("Fragment WeatherWeek binding is null")

    private val viewModel by viewModels<WeatherWeekViewModel>()
    private val weatherDayListAdapter = WeatherDayListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherWeekBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.selectedCity?.let {
            binding.toolbar.subtitle = "${it.name}, ${it.country}"
        }
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        viewModel.validationMessage.observe(viewLifecycleOwner){
            context?.showMessage(it)
        }

        viewModel.weatherWeek.observe(viewLifecycleOwner){
            weatherDayListAdapter.submitList(it)
        }
        binding.rvWeatherDayList.adapter = weatherDayListAdapter

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() =
            WeatherWeekFragment()
    }
}