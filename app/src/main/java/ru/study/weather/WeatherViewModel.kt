package ru.study.weather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ru.study.weather.model.WeatherData

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val weatherRepository: WeatherRepository = WeatherRepository(getApplication())
    val weatherData: LiveData<WeatherData>? = weatherRepository.getWeatherData()
}