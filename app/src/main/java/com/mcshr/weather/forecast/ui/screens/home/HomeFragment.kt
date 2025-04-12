package com.mcshr.weather.forecast.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mcshr.weather.forecast.R
import com.mcshr.weather.forecast.databinding.FragmentHomeBinding
import com.mcshr.weather.forecast.ui.screens.select_city.SelectCityFragment
import com.mcshr.weather.forecast.ui.screens.weather_2weeks.WeatherTwoWeeksFragment
import com.mcshr.weather.forecast.ui.screens.weather_today.WeatherTodayFragment
import com.mcshr.weather.forecast.ui.screens.weather_week.WeatherWeekFragment
import com.mcshr.weather.forecast.ui.utils.setOnClickListenerWithDelay
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

        binding.btnSelectCity.setOnClickListenerWithDelay {
            navigateTo(SelectCityFragment.newInstance())
        }
        binding.btnWeatherToday.setOnClickListenerWithDelay {
            handleNavigation{
                navigateTo(WeatherTodayFragment.newInstance())
            }
        }
        binding.btnWeatherWeek.setOnClickListenerWithDelay {
            handleNavigation{
                navigateTo(WeatherWeekFragment.newInstance())
            }
        }
        binding.btnWeather2Weeks.setOnClickListenerWithDelay {
            handleNavigation{
                navigateTo(WeatherTwoWeeksFragment.newInstance())
            }
        }
    }
    private fun handleNavigation(destination: ()->Unit){
        if(viewModel.isCityExist()){
            destination()
        }else{
            context?.showMessage(getString(R.string.error_no_city))
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