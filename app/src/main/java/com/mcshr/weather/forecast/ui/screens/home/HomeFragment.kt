package com.mcshr.weather.forecast.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mcshr.weather.forecast.R
import com.mcshr.weather.forecast.databinding.FragmentHomeBinding
import com.mcshr.weather.forecast.ui.screens.WeatherTodayFragment
import com.mcshr.weather.forecast.ui.screens.WeatherTwoWeeksFragment
import com.mcshr.weather.forecast.ui.screens.WeatherWeekFragment
import com.mcshr.weather.forecast.ui.screens.select_city.SelectCityFragment
import com.mcshr.weather.forecast.ui.utils.showMessage

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding ?:throw RuntimeException("FragmentHome binding is null")

    private val viewModel by viewModels<HomeViewModel>()


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
            if(viewModel.isCityExist()) {
                navigateTo(WeatherTodayFragment.newInstance())
            }else {
                context?.showMessage("First, select a city")
            }
        }
        binding.btnWeatherWeek.setOnClickListener {
            if(viewModel.isCityExist()) {
                navigateTo(WeatherWeekFragment.newInstance())
            }else {
                context?.showMessage("First, select a city")
            }
        }
        binding.btnWeather2Weeks.setOnClickListener {
            if(viewModel.isCityExist()) {
                navigateTo(WeatherTwoWeeksFragment.newInstance())
            }else {
                context?.showMessage("First, select a city")
            }

        }
    }
    private fun handleNavigation(){}



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