package com.mcshr.weather.forecast.ui.screens.select_city

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mcshr.weather.forecast.data.WeatherRepositoryImpl
import com.mcshr.weather.forecast.data.WeatherSharedPreferences
import com.mcshr.weather.forecast.domain.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class SelectCityViewModel(application: Application) : AndroidViewModel(application) {
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
                _validationMessage.postValue("Selected city: ${city.name} - ${city.country}")
                repository.saveSelectedCityName(city.name)
                _readyToClose.postValue(Unit)
            } catch (e: UnknownHostException) {
                _validationMessage.postValue("No internet connection")
            } catch (e: SocketTimeoutException){
                _validationMessage.postValue("Waiting time expired. Try again later.")
            } catch (e: NoSuchElementException) {
                _validationMessage.postValue("City not found")
            }
            catch (e: HttpException) {
                val message = when (e.code()) {
                    429 -> "Request limit exceeded"
                    else -> "Server error: ${e.code()}"
                }
                _validationMessage.postValue(message)
            }
            catch (e: Exception) {
                _validationMessage.postValue("Unknown exception: $e")
                Log.d("error", e.toString())
            }
        }
    }
}