package com.exail.intervaltimer

import android.app.Application
import android.util.Log
import com.exail.intervaltimer.koin.appModule
import com.exail.intervaltimer.utils.LogUtils
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

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
        Timber.plant(if (BuildConfig.DEBUG) DebugTree() else ProductionTree())
    }
}

//region TREES
/*
                                    _
                                   ('<
                                  \(_)
                                  # #### ####
                                ### \/#|### |/####
                               ##\/#/ \||/##/_/##/_#
                             ###  \/###|/ \/ # ###
                           ##_\_#\_\## | #/###_/_####
                          ## #### # \ #| /  #### ##/##
                           __#_--###`  |{,###---###-~
                                     \ }{     (
                                      }}{    / \
                                      }}{   (   )
                                      }}{    `"'
                                      }}{
                                      }}{
                                      }{}
                                , -=-~{ .-^- _
                                      `}
                                       {
                              Take care for trees
 */

/**
 * Custom tree for production, will log only errors, warning and debug
 */
class ProductionTree : Timber.Tree() {
    override fun isLoggable(tag: String?, priority: Int) =
        priority == Log.ERROR || priority == Log.WARN

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) =
        when (priority) {
            Log.ERROR -> LogUtils.error(tag ?: "", message, t)
            Log.WARN -> LogUtils.warn(tag ?: "", message, t)
            else -> LogUtils.debug(tag ?: "", message, t)
        }
}

/**
 * Custom tree for debug, will ne logging if build is debug
 */
class DebugTree : Timber.DebugTree() {
    override fun isLoggable(tag: String?, priority: Int) = BuildConfig.DEBUG

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) =
        when (priority) {
            Log.DEBUG -> LogUtils.debug(tag ?: "", message, t)
            Log.ERROR -> LogUtils.error(tag ?: "", message, t)
            Log.WARN -> LogUtils.warn(tag ?: "", message, t)
            Log.INFO -> LogUtils.info(tag ?: "", message, t)
            Log.VERBOSE -> LogUtils.verbose(tag ?: "", message, t)
            else -> LogUtils.debug(tag ?: "", message, t)
        }
}
//endregion