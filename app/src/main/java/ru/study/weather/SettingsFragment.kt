package ru.study.weather

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.settings_page.*

class SettingsFragment : Fragment() {
    private val viewModel: WeatherViewModel by viewModels(factoryProducer = {
        SavedStateViewModelFactory(requireActivity().application,this) })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.settings_page, container, false)

        val textLayout = view.findViewById<TextInputLayout>(R.id.text_layout)
        textLayout.visibility = View.GONE

        val sharedPreference = activity?.getSharedPreferences(
                "settings", Context.MODE_PRIVATE)
        when (sharedPreference?.getString("city", "")) {
            "Moskva" -> view.findViewById<RadioButton>(R.id.radioButton).isChecked = true
            "Sankt-Peterburg" -> view.findViewById<RadioButton>(R.id.radioButton2).isChecked = true
            else -> { view.findViewById<RadioButton>(R.id.radioButton3).isChecked = true
                textLayout.visibility = View.VISIBLE }
        }

        view.findViewById<RadioButton>(R.id.radioButton).apply {
            text = "Москва"
            setOnClickListener { button ->
                textLayout.visibility = View.GONE
                sharedPreference?.let {
                    Utils.setSharedPrefValue(sharedPreference, "city", "Moskva")
                }
                viewModel.retry()
            }
        }
        view.findViewById<RadioButton>(R.id.radioButton2).apply {
            text = "Санкт-Петербург"
            setOnClickListener { button ->
                textLayout.visibility = View.GONE
                sharedPreference?.let {
                    Utils.setSharedPrefValue(sharedPreference, "city", "Sankt-Peterburg")
                }
                viewModel.retry()
            }
        }
        view.findViewById<RadioButton>(R.id.radioButton3).apply {
            text = "Другой город"
            setOnClickListener {
                textLayout.visibility = View.VISIBLE
            }
        }

        view.findViewById<EditText>(R.id.editText).setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
               sharedPreference?.let {
                   Utils.setSharedPrefValue(sharedPreference, "city", editText.text.toString())
                }
                viewModel.retry()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
        return view
    }

    companion object {
        fun newInstance(): Fragment {
            return SettingsFragment()
        }
    }
}