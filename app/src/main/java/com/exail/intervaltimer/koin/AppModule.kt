package com.exail.intervaltimer.koin

import android.preference.PreferenceManager
import com.exail.intervaltimer.data.AppCache
import com.exail.intervaltimer.view.model.TimerViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by eduardsdenisjonoks  on 2019-06-06.
 */

val appModule = module {
    single { AppCache(PreferenceManager.getDefaultSharedPreferences(get())) }

    viewModel { TimerViewModel(get()) }
}