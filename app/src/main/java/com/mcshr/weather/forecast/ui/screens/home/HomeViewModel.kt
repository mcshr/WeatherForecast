package com.mcshr.weather.forecast.ui.screens.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mcshr.weather.forecast.data.WeatherRepositoryImpl
import com.mcshr.weather.forecast.data.WeatherSharedPreferences

class HomeViewModel(application: Application):AndroidViewModel(application) {
    private val repository = WeatherRepositoryImpl(WeatherSharedPreferences(application))

    fun isCityExist():Boolean{
        return !repository.getSelectedCityName().isNullOrBlank()
    }
}