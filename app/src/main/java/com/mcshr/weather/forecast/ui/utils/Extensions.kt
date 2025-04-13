package com.mcshr.weather.forecast.ui.utils

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import com.mcshr.weather.forecast.R
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

private const val DELAY = 1000L

fun View.setOnClickListenerWithDelay(onClick: (View) -> Unit){
    var canClick = true
    setOnClickListener{
        if(canClick){
            canClick = false
            onClick(it)
            it.postDelayed({canClick = true}, DELAY)
        }
    }
}

 fun Context.showMessage(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun Exception.handleNetworkException(application: Application): String? {
    return when (this) {
        is UnknownHostException -> application.getString(R.string.error_no_internet)

        is SocketTimeoutException -> application.getString(R.string.error_timeout)

        is HttpException -> {
            val message = when (this.code()) {
                429 -> application.getString(R.string.error_429)
                else -> application.getString(R.string.error_server, this.code().toString())
            }
            message
        }

        else -> null
    }
}


