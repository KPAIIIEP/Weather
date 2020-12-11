package ru.study.weather.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.study.weather.model.WeatherDetails

class WeatherDetailsConverter {
    @TypeConverter
    fun fromJson(json: String) : List<WeatherDetails> {
        val type = object : TypeToken<List<WeatherDetails>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(weatherDetails: List<WeatherDetails>) : String {
        val type = object : TypeToken<List<WeatherDetails>>() {}.type
        return Gson().toJson(weatherDetails, type)
    }
}