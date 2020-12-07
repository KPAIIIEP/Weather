package ru.study.weather.model

import com.google.gson.annotations.SerializedName

data class JsonRoot(
        @SerializedName("city")
        var city: City,
        @SerializedName("list")
        var weatherData: List<WeatherData>
)