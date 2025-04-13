package com.mcshr.weather.forecast.ui.screens.select_city

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
import com.mcshr.weather.forecast.ui.utils.handleNetworkException
import kotlinx.coroutines.launch

class SelectCityViewModel(private val application: Application) : AndroidViewModel(application) {
    private val repository: WeatherRepository = WeatherRepositoryImpl(
        WeatherSharedPreferences(context = application)
    )

    private val _validationMessage = MutableLiveData<String>()
    val validationMessage: LiveData<String>
        get() = _validationMessage

    private val _readyToClose = MutableLiveData<Unit>()
    val readyToClose: LiveData<Unit>
        get() = _readyToClose


    fun selectCity(cityName: String) {
        viewModelScope.launch {
            try {  //TODO make sealed class for result (success and error)
                val city = repository.getCityByName(cityName)
                _validationMessage.postValue(
                    application.getString(
                        R.string.selected_city,
                        city.name,
                        city.country
                    ))
                repository.saveSelectedCity(city)
                _readyToClose.postValue(Unit)
            }
            catch (e: Exception) {
                e.handleNetworkException(application)?.let {
                    _validationMessage.postValue(it)
                } ?: run {
                    when(e){
                        is NoSuchElementException ->  _validationMessage.postValue(application.getString(R.string.error_invalid_city_name))
                        else ->{
                            _validationMessage.postValue(application.getString(R.string.error_unknown, e))
                            Log.d("error", e.toString())
                        }
                    }
                }
            }
        }
    }

}