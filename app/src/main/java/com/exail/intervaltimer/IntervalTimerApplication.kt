package com.exail.intervaltimer

import android.app.Application
import com.exail.intervaltimer.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by eduardsdenisjonoks  on 2019-06-06.
 */
class IntervalTimerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@IntervalTimerApplication)
            modules(appModule)
        }
    }
}