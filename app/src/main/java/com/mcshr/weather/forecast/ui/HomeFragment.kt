package com.mcshr.weather.forecast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mcshr.weather.forecast.R
import com.mcshr.weather.forecast.databinding.FragmentHomeBinding
import com.mcshr.weather.forecast.ui.select_city.SelectCityFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding ?:throw RuntimeException("FragmentHome binding is null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSelectCity.setOnClickListener {
            navigateTo(SelectCityFragment.newInstance())
        }
        binding.btnWeatherToday.setOnClickListener {
            navigateTo(WeatherTodayFragment.newInstance())
        }
        binding.btnWeatherWeek.setOnClickListener {
            navigateTo(WeatherWeekFragment.newInstance())
        }
        binding.btnWeather2Weeks.setOnClickListener {
            navigateTo(WeatherTwoWeeksFragment.newInstance())
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun navigateTo(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(null)
            .commit()
    }

}