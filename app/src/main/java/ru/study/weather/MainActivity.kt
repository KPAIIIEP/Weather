package ru.study.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager
        var fragment: Fragment? = fragmentManager.findFragmentById(R.id.fragment_contaner)

        fragment?: run {
            fragment = createFragment()
            Log.d("MainActivity", "create")
            fragment?.let {
                fragmentManager.beginTransaction().add(R.id.fragment_contaner, it).commit()
            }
        }
    }

    private fun createFragment(): Fragment {
        return MainFragment.newInstance()
    }
}