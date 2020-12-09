package ru.study.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ForecastFragment : Fragment() {
    private val viewModel: WeatherViewModel by viewModels(factoryProducer = {
        SavedStateViewModelFactory(requireActivity().application,this) })
    lateinit var weatherAdapter: WeatherAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.forecast_page, container, false)
        val linearLayoutManager = LinearLayoutManager(activity)
        weatherAdapter = WeatherAdapter(mutableListOf())
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = linearLayoutManager
            adapter = weatherAdapter
            viewModel.weatherData.observe(viewLifecycleOwner) {
                // 1-й элемент выводится в заголовок, поэтому - sublist
                weatherAdapter.weatherDetails = it.weatherDetails.subList(1, it.weatherDetails.size)
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
        return view
    }

    companion object {
        fun newInstance(): Fragment {
            return ForecastFragment()
        }
    }
}