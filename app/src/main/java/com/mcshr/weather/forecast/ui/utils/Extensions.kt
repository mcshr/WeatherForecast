package com.mcshr.weather.forecast.ui.utils

import android.content.Context
import android.view.View
import android.widget.Toast

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

