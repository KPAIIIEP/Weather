package ru.study.weather.model

import com.google.gson.annotations.SerializedName

data class WeatherData(
        @SerializedName("city")
        var city: City,
        @SerializedName("list")
        var weatherDetails: List<WeatherDetails>
)