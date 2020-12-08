package ru.study.weather

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.study.weather.model.WeatherData

private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"

interface WeatherApi {
    @GET("forecast/daily?lang=ru")
    fun getForecastDaily(@Query(value = "q", encoded = true) q: String = "Moskva",
                         @Query("units") units: String = "metric",
                         @Query("cnt") cnt: Int = 7,
                         @Query("appid") appId: String = API_KEY): Call<WeatherData>

    companion object {
        val weatherApi: WeatherApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}