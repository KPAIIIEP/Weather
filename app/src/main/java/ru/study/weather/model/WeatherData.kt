package ru.study.weather.model

import androidx.room.*
import com.google.gson.annotations.SerializedName
import ru.study.weather.database.WeatherDetailsConverter

@Entity(tableName = "weather_data")
@TypeConverters(WeatherDetailsConverter::class)
data class WeatherData(
        @PrimaryKey(autoGenerate = true)
        var weatherId: Int = 0,
        var dateTime: Long = System.currentTimeMillis(),
        @Embedded
        @SerializedName("city")
        var city: City = City(0,""),
        @SerializedName("list")
        var weatherDetails: List<WeatherDetails> = listOf()
)