package ru.study.weather

import android.os.Bundle
import androidx.fragment.app.Fragment

class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    companion object {
        fun newInstance(): Fragment {
            return MainFragment()
        }
    }
}