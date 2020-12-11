package ru.study.weather.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ru.study.weather.model.WeatherData

@Dao
interface WeatherDao {
    @Insert(onConflict = REPLACE)
    fun save(weatherData: WeatherData)

    @Query("DELETE FROM weather_data")
    fun deleteAll()

    @Query("SELECT * FROM weather_data")
    fun load(): LiveData<WeatherData>
}