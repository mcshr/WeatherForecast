package com.mcshr.weather.forecast.ui.screens.weather_today

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mcshr.weather.forecast.R
import com.mcshr.weather.forecast.data.WeatherRepositoryImpl
import com.mcshr.weather.forecast.data.WeatherSharedPreferences
import com.mcshr.weather.forecast.domain.WeatherRepository
import com.mcshr.weather.forecast.domain.entities.WeatherToday
import com.mcshr.weather.forecast.ui.utils.handleNetworkException
import kotlinx.coroutines.launch

class WeatherTodayViewModel(private val application: Application) : AndroidViewModel(application) {
    private val repository: WeatherRepository = WeatherRepositoryImpl(
        WeatherSharedPreferences(context = application)
    )

    val selectedCity = repository.getSelectedCity()

    private val _validationMessage = MutableLiveData<String>()
    val validationMessage: LiveData<String>
        get() = _validationMessage

    private val _weatherToday = MutableLiveData<WeatherToday>()
    val weatherToday: LiveData<WeatherToday>
        get() = _weatherToday


    init {
        viewModelScope.launch {
            try {
                val weather = selectedCity?.let {
                    repository.getWeatherToday(it)
                }
                weather?.let {
                    _weatherToday.postValue(it)
                }
            } catch (e: Exception) {
                e.handleNetworkException(application)?.let {
                    _validationMessage.postValue(it)
                } ?: run {
                    _validationMessage.postValue(application.getString(R.string.error_unknown, e))
                    Log.d("error", e.toString())
                }
            }
        }
    }
}

