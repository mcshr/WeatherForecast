package com.mcshr.weather.forecast.ui.screens.weather_2weeks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mcshr.weather.forecast.databinding.FragmentWeatherTwoWeeksBinding
import com.mcshr.weather.forecast.ui.utils.showMessage
import com.mcshr.weather.forecast.ui.weather_adapters.weather_day.WeatherDayListAdapter


class WeatherTwoWeeksFragment : Fragment() {

    private var _binding: FragmentWeatherTwoWeeksBinding? = null
    private val binding
        get() = _binding ?:throw RuntimeException("Fragment WeatherTwoWeeks binding is null")

    private val viewModel by viewModels<WeatherTwoWeeksViewModel>()
    private val weatherDayListAdapter = WeatherDayListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherTwoWeeksBinding.inflate(inflater, container, false)
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
            WeatherTwoWeeksFragment()
    }
}