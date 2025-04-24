package com.mcshr.weather.forecast.ui.screens.home

import androidx.lifecycle.ViewModel
import com.mcshr.weather.forecast.domain.interactors.GetSelectedCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSelectedCity: GetSelectedCityUseCase
): ViewModel() {
    fun isCityExist():Boolean{
        return getSelectedCity() != null
    }
}