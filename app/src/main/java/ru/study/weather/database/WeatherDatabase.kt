package ru.study.weather.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.study.weather.model.WeatherData

@Database(entities = [WeatherData::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        private var instance: WeatherDatabase? = null

        fun getInstance(context: Context) : WeatherDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context,
                    WeatherDatabase::class.java, "weather_database").build()
            }
            return instance
        }
    }
}