package com.mcshr.weather.forecast.ui.select_city

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcshr.weather.forecast.data.WeatherRepositoryImpl
import com.mcshr.weather.forecast.domain.WeatherRepository
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class SelectCityViewModel : ViewModel() {

    private val repository: WeatherRepository = WeatherRepositoryImpl()

    private val _validationMessage = MutableLiveData<String>()
    val validationMessage: LiveData<String>
        get() = _validationMessage


    fun selectCity(cityName: String) {
        viewModelScope.launch {
            try {  //TODO make sealed class for result (success and error)
                val city = repository.getCityByName(cityName)
                _validationMessage.postValue("Selected city: ${city.name}")

            } catch (e: UnknownHostException) {
                _validationMessage.postValue("No internet connection")
            } catch (e: SocketTimeoutException){
                _validationMessage.postValue("Waiting time expired. Try again later.")
            } catch (e: NoSuchElementException) {
                _validationMessage.postValue("City not found")
            } catch (e: Exception) {
                _validationMessage.postValue("Unknown exception: $e")
                Log.d("error", e.toString())
            }
        }
    }
}