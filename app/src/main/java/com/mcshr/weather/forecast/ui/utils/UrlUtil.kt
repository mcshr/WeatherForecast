package com.mcshr.weather.forecast.ui.utils
import android.os.Build

/**
 * Повертає URL зображення залежно від версії Android.
 *
 * Для забезпечення сумісності з різними версіями Android:
 * - На старих пристроях (API 21-24) використовується HTTP, оскільки вони можуть не підтримувати сучасні протоколи TLS (наприклад, TLS 1.2).
 * - На нових пристроях (API 25+) використовується HTTPS, оскільки cleartext traffic (HTTP) заборонено на Android 9+ (API 28+) за замовчуванням.
 */

fun getImageUrl(iconCode: String): String {
    val baseUrl = if (Build.VERSION.SDK_INT in 21..24) {
        "http://openweathermap.org/img/wn/$iconCode@2x.png"
    } else {
        "https://openweathermap.org/img/wn/$iconCode@2x.png"
    }
    return baseUrl
}

