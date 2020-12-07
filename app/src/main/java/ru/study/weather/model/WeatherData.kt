package ru.study.weather.model

import com.google.gson.annotations.SerializedName
import ru.study.weather.model.Temperature
import ru.study.weather.model.Weather

data class WeatherData(
        @SerializedName("dt")
        var dateTime: Int,
        @SerializedName("temp")
        var temp: Temperature,
        @SerializedName("pressure")
        var pressure: Int,
        @SerializedName("humidity")
        var humidity: Int,
        @SerializedName("weather")
        var weather: List<Weather>,
        @SerializedName("speed")
        var speed: Double,
        @SerializedName("deg")
        var degrees: Int,
)