package com.mcshr.weather.forecast.data

import android.content.Context

class WeatherSharedPreferences(context: Context) {
    private val sharedPrefs = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    fun saveSelectedCity(cityName:String){
        sharedPrefs.edit().putString(KEY_SELECTED_CITY, cityName).apply()
    }

    fun getSelectedCity():String?{
        return sharedPrefs.getString(KEY_SELECTED_CITY, null)
    }

    companion object{
        private const val NAME = "WeatherPrefs^(0o0)^"
        private const val KEY_SELECTED_CITY = "SelectedCITY"
    }

}