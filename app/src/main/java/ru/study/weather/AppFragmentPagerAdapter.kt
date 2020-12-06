package ru.study.weather

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class AppFragmentPagerAdapter(fm: FragmentManager, context: Context) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val pageCount = 2
    private val tabTitles: List<String> = listOf("Прогноз", "Настройки")
    private val context: Context = context

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ForecastFragment.newInstance()
            else -> SettingsFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return pageCount
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles.get(position)
    }
}