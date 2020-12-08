package ru.study.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.study.weather.model.WeatherData

class WeatherViewModel : ViewModel() {
    private val weatherRepository: WeatherRepository = WeatherRepository()
    val weatherData: LiveData<WeatherData> = weatherRepository.getForecastDaily()
}