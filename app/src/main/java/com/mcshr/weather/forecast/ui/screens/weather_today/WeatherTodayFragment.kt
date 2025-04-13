package com.mcshr.weather.forecast.ui.screens.weather_today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.mcshr.weather.forecast.R
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


        viewModel.validationMessage.observe(viewLifecycleOwner){
            context?.showMessage(it)
        }
        viewModel.weatherToday.observe(viewLifecycleOwner){
            binding.tvTemp.text = getString(R.string.temperature, it.mainData.temp.toInt().toString())
            binding.tvTempRealFeel.text = getString(R.string.feels_like, it.mainData.feelsLike.toString())
//            Log.d("ICON", it.weather.first().iconUrl)
            binding.imageViewWeatherIcon.load(it.weather.first().iconUrl) {
                placeholder(R.drawable.placeholder)
                error(R.drawable.error_image)
            }

            binding.tvWeatherDescription.text = it.weather.first().description
            binding.tvHumidity.text = getString(R.string.humidity, it.mainData.humidity.toString())
            binding.tvPressure.text = getString(R.string.pressure, it.mainData.pressure.toString())
            binding.tvWind.text = getString(R.string.wind, it.wind.speed.toString(), it.wind.deg.toString())
            binding.tvClouds.text = getString(R.string.clouds, it.clouds.toString())
            binding.tvRain.text = getString(
                R.string.rain,
                it.rain?.oneHour?.toString() ?: "No rain"
            )

        }
        
        super.onViewCreated(view, savedInstanceState)
    }



    companion object {
        fun newInstance() =
            WeatherTodayFragment()
    }
}