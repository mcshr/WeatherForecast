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
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class WeatherTodayViewModel(private val application: Application) : AndroidViewModel(application) {
    private val repository: WeatherRepository = WeatherRepositoryImpl(
        WeatherSharedPreferences(context = application)
    )

    val selectedCity = repository.getSelectedCity()
    private val _validationMessage = MutableLiveData<String>()
    val validationMessage: LiveData<String>
        get() = _validationMessage

    fun getWeather() {
        viewModelScope.launch {
            try {  //TODO make sealed class for result (success and error)
                val weather = selectedCity?.let { repository.getWeatherToday(it) }
                _validationMessage.postValue(weather?.weather.toString())

            } catch (e: UnknownHostException) {
                _validationMessage.postValue(application.getString(R.string.error_no_internet))
            } catch (e: SocketTimeoutException){
                _validationMessage.postValue(application.getString(R.string.error_timeout))
            } catch (e: NoSuchElementException) {
                _validationMessage.postValue(application.getString(R.string.error_invalid_city_name))
            }
            catch (e: HttpException) {
                val message = when (e.code()) {
                    429 -> application.getString(R.string.error_429)
                    else -> application.getString(R.string.error_server, e.code().toString())
                }
                _validationMessage.postValue(message)
            }
            catch (e: Exception) {
                _validationMessage.postValue(application.getString(R.string.error_unknown, e))
                Log.d("error", e.toString())
            }
        }
    }

}