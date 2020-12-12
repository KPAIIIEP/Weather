package ru.study.weather

import android.content.SharedPreferences
import java.text.DateFormat
import java.util.*

class Utils {
    companion object {
        fun formatDate(date: Int): String {
            return DateFormat.getDateInstance().format(Date(date.toLong() * 1000))
        }

        fun getWeatherConditionIcon(main: String, id: Int) : Int {
            return when (main) {
                "Clear" -> R.drawable.ic_clear
                "Clouds" -> if (id == 801) R.drawable.ic_few_clouds else R.drawable.ic_broken_clouds
                "Rain" -> R.drawable.ic_rain
                "Thunderstorm" -> R.drawable.ic_thunderstorm
                "Snow" -> R.drawable.ic_snow
                else -> R.drawable.ic_broken_clouds // нет иконки
            }
        }

        fun getWindDirection(degrees: Int) : String {
            return when (degrees) {
                in 337..360 -> "C"
                in 292..336 -> "C-З"
                in 247..291 -> "З"
                in 202..246 -> "Ю-З"
                in 157..201 -> "Ю"
                in 122..156 -> "Ю-В"
                in 67..121 -> "В"
                in 22..66 -> "С-В"
                else -> "С"
            }
        }

        fun setSharedPrefValue(sharedPreferences: SharedPreferences,
                               key: String, value: String) {
            val editor = sharedPreferences.edit()
            editor.putString(key, value)
            editor.apply()
        }
    }
}