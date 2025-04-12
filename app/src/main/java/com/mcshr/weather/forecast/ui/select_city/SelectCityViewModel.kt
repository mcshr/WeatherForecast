package com.mcshr.weather.forecast.ui.select_city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcshr.weather.forecast.data.WeatherRepositoryImpl
import com.mcshr.weather.forecast.domain.WeatherRepository
import kotlinx.coroutines.launch

class SelectCityViewModel: ViewModel() {

    private val repository: WeatherRepository = WeatherRepositoryImpl()

    private val _validationMessage = MutableLiveData<String>()
    val validationMessage: LiveData<String>
        get() = _validationMessage


    fun selectCity(cityName:String){
        viewModelScope.launch {
            try {
                repository.getCityByName(cityName)
            } catch (e: Exception) { //TODO make sealed class for result (success and error)
                _validationMessage.postValue(e.toString())
            }
        }
    }
}