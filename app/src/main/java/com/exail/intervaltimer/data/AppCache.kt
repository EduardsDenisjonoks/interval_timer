package com.exail.intervaltimer.data

import android.content.SharedPreferences
import androidx.annotation.RawRes
import androidx.appcompat.app.AppCompatDelegate
import com.exail.intervaltimer.R

/**
 * Created by eduardsdenisjonoks  on 2019-06-06.
 */
class AppCache(private val prefs: SharedPreferences) {

    companion object {
        private const val APP_MODE = "com.exail.intervaltimer.data.app_mode"
        private const val INTERVAL = "com.exail.intervaltimer.data.interval"
        private const val VIBRATION = "com.exail.intervaltimer.data.vibration"
        private const val SOUND = "com.exail.intervaltimer.data.sound"
    }

    //region APP MODE - DARK or LIGHT side, what will you choose
    fun selectAppMode(mode: Int) = prefs.edit().putInt(APP_MODE, mode).apply()

    fun getAppMode() = prefs.getInt(APP_MODE, AppCompatDelegate.MODE_NIGHT_YES)
    //endregion

    //region INTERVAL - remember las used interval
    fun setInterval(interval: Long) = prefs.edit().putLong(INTERVAL, interval).apply()

    fun getInterval() = prefs.getLong(INTERVAL, 0L)
    //endregion

    //region VIBRATION - enable disable vibration on  interval
    fun setVibration(isEnabled: Boolean) = prefs.edit().putBoolean(VIBRATION, isEnabled).apply()

    fun getVibration() = prefs.getBoolean(VIBRATION, true)
    //endregion

    //region SOUND - sound res id
    fun setSound(@RawRes sound: Int) = prefs.edit().putInt(SOUND, sound).apply()

    fun getSound() = prefs.getInt(SOUND, R.raw.answering_machine_bee)
    //endregion
}