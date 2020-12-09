package ru.study.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.SavedStateViewModelFactory

import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout
import java.util.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private val viewModel: WeatherViewModel by viewModels(factoryProducer = {
        SavedStateViewModelFactory(application,this) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        val viewPager: ViewPager = findViewById(R.id.viewpager)
        viewPager.adapter = AppFragmentPagerAdapter(supportFragmentManager, this)

        val tabLayout: TabLayout = findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)

        viewModel.weatherData.observe(this) {
            // update UI
            val formatDate = Utils.formatDate(it.weatherDetails[0].dateTime)
            val weather = it.weatherDetails[0].weather[0].description.capitalize(Locale.ROOT)
            val speedWind = it.weatherDetails[0].speed
            val degrees = it.weatherDetails[0].degrees
            val humidity = it.weatherDetails[0].humidity
            val pressure = (it.weatherDetails[0].pressure * 0.75).toInt()
            val details = "Погода на $formatDate\n" +
                    "$weather\n" +
                    "Ветер: $speedWind м/с, $degrees\n" +
                    "Влажность: $humidity%\n" +
                    "Давление: $pressure мм рт.с."
            findViewById<TextView>(R.id.collapse_text).text = details

            val (id, main) = it.weatherDetails[0].weather[0]
            findViewById<ImageView>(R.id.toolbarImage)
                    .setImageResource(Utils.getWeatherConditionIcon(main, id))

            val (day, night) = it.weatherDetails[0].temp
            val toolbarTitleText = it.city.name + " " +
                    day.roundToInt().toString() + "° / " +
                    night.roundToInt().toString() + "°"
            findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = toolbarTitleText
        }
    }
}