package ru.study.weather

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
                "Thunderstorm" -> R.drawable.ic_rain // нет иконки
                "Snow" -> R.drawable.ic_snow
                else -> R.drawable.ic_broken_clouds // нет иконки
            }
        }
    }
}