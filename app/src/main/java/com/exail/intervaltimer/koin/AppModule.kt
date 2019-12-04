package com.exail.intervaltimer.koin

import android.content.Context
import android.os.Vibrator
import androidx.preference.PreferenceManager
import com.exail.intervaltimer.data.AppCache
import com.exail.intervaltimer.view.model.TimerViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by eduardsdenisjonoks  on 2019-06-06.
 */

val appModule = module {
    single { AppCache(PreferenceManager.getDefaultSharedPreferences(get())) }
    single { getVibrator(get()) }

    viewModel { TimerViewModel(get(), get()) }
}


private fun getVibrator(context: Context) : Vibrator{
    return context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
}