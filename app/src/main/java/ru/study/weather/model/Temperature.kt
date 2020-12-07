package ru.study.weather.model

import com.google.gson.annotations.SerializedName

data class Temperature(
        @SerializedName("day")
        var day: Double,
        @SerializedName("night")
        var night: Double
)