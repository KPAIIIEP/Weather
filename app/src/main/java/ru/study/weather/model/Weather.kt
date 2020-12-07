package ru.study.weather.model

import com.google.gson.annotations.SerializedName

data class Weather(
        @SerializedName("main")
        var main: String,
        @SerializedName("description")
        var description: String,
)