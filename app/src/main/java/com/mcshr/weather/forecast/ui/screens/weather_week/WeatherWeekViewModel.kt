package com.mcshr.weather.forecast.ui.screens.weather_week

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcshr.weather.forecast.R
import com.mcshr.weather.forecast.domain.entities.WeatherForecastDay
import com.mcshr.weather.forecast.domain.interactors.GetSelectedCityUseCase
import com.mcshr.weather.forecast.domain.interactors.GetWeatherWeekUseCase
import com.mcshr.weather.forecast.ui.utils.handleNetworkException
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherWeekViewModel
    @Inject constructor(
    getSelectedCity: GetSelectedCityUseCase,
    private val getWeatherWeek: GetWeatherWeekUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    val selectedCity = getSelectedCity()

    private val _validationMessage = MutableLiveData<String>()
    val validationMessage: LiveData<String>
        get() = _validationMessage

    private val _weatherWeek = MutableLiveData<List<WeatherForecastDay>>()
    val weatherWeek: LiveData<List<WeatherForecastDay>>
        get() = _weatherWeek

    init {
        viewModelScope.launch {
            try {
                val weather = selectedCity?.let {
                   getWeatherWeek(it)
                }
                weather?.let {
                    _weatherWeek.postValue(it)
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