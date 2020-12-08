package ru.study.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.study.weather.model.WeatherData

class WeatherRepository {
    private val weatherApi: WeatherApi = WeatherApi.weatherApi

    fun getForecastDaily(): LiveData<WeatherData> {
        val data = MutableLiveData<WeatherData>()
        weatherApi.getForecastDaily()
            .enqueue(object : Callback<WeatherData> {
                override fun onFailure(call: Call<WeatherData>, t: Throwable) {

                }
                override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                    data.value = response.body()
                }
            })
        return data
    }
}