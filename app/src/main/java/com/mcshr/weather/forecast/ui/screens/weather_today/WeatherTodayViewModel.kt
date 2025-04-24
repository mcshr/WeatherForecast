package com.mcshr.weather.forecast.ui.screens.weather_today

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcshr.weather.forecast.R
import com.mcshr.weather.forecast.domain.entities.WeatherForecastItem
import com.mcshr.weather.forecast.domain.interactors.GetSelectedCityUseCase
import com.mcshr.weather.forecast.domain.interactors.GetWeatherCurrentUseCase
import com.mcshr.weather.forecast.ui.utils.handleNetworkException
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherTodayViewModel
    @Inject constructor(
    getSelectedCity: GetSelectedCityUseCase,
    private val getWeatherToday: GetWeatherCurrentUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    val selectedCity = getSelectedCity()

    private val _validationMessage = MutableLiveData<String>()
    val validationMessage: LiveData<String>
        get() = _validationMessage

    private val _weatherForecastItem = MutableLiveData<WeatherForecastItem>()
    val weatherForecastItem: LiveData<WeatherForecastItem>
        get() = _weatherForecastItem


    init {
        viewModelScope.launch {
            try {
                val weather = selectedCity?.let {
                    getWeatherToday(it)
                }
                weather?.let {
                    _weatherForecastItem.postValue(it)
                }
            } catch (e: Exception) {
                e.handleNetworkException(context)?.let {
                    _validationMessage.postValue(it)
                } ?: run {
                    _validationMessage.postValue(context.getString(R.string.error_unknown, e))
                    Log.d("error", e.toString())
                }
            }
        }
    }
}

