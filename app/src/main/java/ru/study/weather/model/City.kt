package ru.study.weather.model

import com.google.gson.annotations.SerializedName

class City(
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String
)