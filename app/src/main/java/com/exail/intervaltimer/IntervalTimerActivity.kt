package com.exail.intervaltimer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.exail.intervaltimer.data.AppCache
import org.koin.android.ext.android.inject


class IntervalTimerActivity : AppCompatActivity() {

    private val appCache: AppCache by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAppMode()
        setContentView(R.layout.activity_interval_timer)
    }

    private fun initAppMode() {
        if (appCache.getAppMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
