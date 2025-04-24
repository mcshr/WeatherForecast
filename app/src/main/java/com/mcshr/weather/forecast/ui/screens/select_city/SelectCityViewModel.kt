package com.mcshr.weather.forecast.ui.screens.select_city

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcshr.weather.forecast.R
import com.mcshr.weather.forecast.domain.interactors.GetCityByNameUseCase
import com.mcshr.weather.forecast.domain.interactors.SaveSelectedCityUseCase
import com.mcshr.weather.forecast.ui.utils.handleNetworkException
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectCityViewModel @Inject constructor(
    private val saveSelectedCity: SaveSelectedCityUseCase,
    private val getCityByName: GetCityByNameUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _validationMessage = MutableLiveData<String>()
    val validationMessage: LiveData<String>
        get() = _validationMessage

    private val _readyToClose = MutableLiveData<Unit>()
    val readyToClose: LiveData<Unit>
        get() = _readyToClose


    fun selectCity(cityName: String) {
        viewModelScope.launch {
            try {  //TODO make sealed class for result (success and error)
                val city = getCityByName(cityName)
                _validationMessage.postValue(
                    context.getString(
                        R.string.selected_city,
                        city.name,
                        city.country
                    )
                )
                saveSelectedCity(city)
                _readyToClose.postValue(Unit)
            } catch (e: Exception) {
                e.handleNetworkException(context)?.let {
                    _validationMessage.postValue(it)
                } ?: run {
                    when (e) {
                        is NoSuchElementException -> _validationMessage.postValue(
                            context.getString(R.string.error_invalid_city_name)
                        )

                        else -> {
                            _validationMessage.postValue(
                                context.getString(
                                    R.string.error_unknown,
                                    e
                                )
                            )
                            Log.d("error", e.toString())
                        }
                    }
                }
            }
        }
    }

}