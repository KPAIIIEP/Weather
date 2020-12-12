package ru.study.weather

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.study.weather.database.WeatherDao
import ru.study.weather.database.WeatherDatabase
import ru.study.weather.model.WeatherData
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class WeatherRepository(context: Context) {
    private val weatherApi: WeatherApi = WeatherApi.weatherApi
    private val executor: Executor = Executors.newSingleThreadExecutor()
    private val weatherDao: WeatherDao? = WeatherDatabase.getInstance(context)?.weatherDao()
    private val sharedPreferences: SharedPreferences = context
            .getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun getWeatherData(): LiveData<WeatherData>? {
        refreshWeatherData()
        return weatherDao?.load()
    }

    private fun refreshWeatherData() {
        sharedPreferences.getString("city", "")?.let { it ->
            weatherApi.getForecastDaily(q = it)
                .enqueue(object : Callback<WeatherData> {
                    override fun onFailure(call: Call<WeatherData>, t: Throwable) {

                    }
                    override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                        executor.execute {
                            response.body()?.let {
                                weatherDao?.deleteAndSave(it)
                                //weatherDao?.deleteAll()
                                //weatherDao?.save(it)
                            }
                        }
                    }
                })
        }
    }
}