package ru.study.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.study.weather.model.WeatherDetails
import kotlin.math.roundToInt

class WeatherAdapter(var weatherDetails: List<WeatherDetails>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return WeatherHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.bind(weatherDetails[position])
    }

    override fun getItemCount(): Int {
        return weatherDetails.size
    }

    class WeatherHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(weatherDetails: WeatherDetails) {
            itemView.findViewById<TextView>(R.id.textViewDate).text =
                    Utils.formatDate(weatherDetails.dateTime)
            val (id, main) = weatherDetails.weather[0]
            itemView.findViewById<ImageView>(R.id.imageView)
                    .setImageResource(Utils.getWeatherConditionIcon(main, id))
            val (day, night) = weatherDetails.temp
            val formatTemp = day.roundToInt().toString() + "° / " +
                    night.roundToInt().toString() + "°"
            itemView.findViewById<TextView>(R.id.textViewTemp).text = formatTemp

        }
    }
}