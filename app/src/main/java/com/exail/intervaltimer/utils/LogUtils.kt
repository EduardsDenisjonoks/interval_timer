package com.exail.intervaltimer.utils

import android.util.Log
import com.crashlytics.android.Crashlytics

class LogUtils {

    companion object {
        /**
         * If it is debug build will log tag and message, if exception is provided will log exception
         * @param tag - tag as sting to indicate what called this function
         * @param message - message as string
         * @param ex - optional (nullable) exception
         */
        @JvmStatic
        fun debug(tag: String, message: String, ex: Throwable? = null) {
            Crashlytics.log(Log.DEBUG, tag, message)
            ex?.let { Crashlytics.logException(ex) }
        }

        /**
         * Will log error and if exception is provided will log exception
         * @param tag - tag as sting to indicate what called this function
         * @param message - message as string
         * @param ex - optional (nullable) exception
         */
        @JvmStatic
        fun error(tag: String, message: String, ex: Throwable? = null) {
            Crashlytics.log(Log.ERROR, tag, message)
            ex?.let { Crashlytics.logException(ex) }
        }

        /**
         * Will log warning and if exception is provided will log exception
         * @param tag - tag as sting to indicate what called this function
         * @param message - message as string
         * @param ex - optional (nullable) exception
         */
        @JvmStatic
        fun warn(tag: String, message: String, ex: Throwable? = null) {
            Crashlytics.log(Log.WARN, tag, message)
            ex?.let { Crashlytics.logException(ex) }
        }

        /**
         * Will log info and if exception is provided will log exception
         * @param tag - tag as sting to indicate what called this function
         * @param message - message as string
         * @param ex - optional (nullable) exception
         */
        @JvmStatic
        fun info(tag: String, message: String, ex: Throwable? = null) {
            Crashlytics.log(Log.INFO, tag, message)
            ex?.let { Crashlytics.logException(ex) }
        }

        /**
         * Will log verbose and if exception is provided will log exception
         * @param tag - tag as sting to indicate what called this function
         * @param message - message as string
         * @param ex - optional (nullable) exception
         */
        @JvmStatic
        fun verbose(tag: String, message: String, ex: Throwable? = null) {
            Crashlytics.log(Log.VERBOSE, tag, message)
            ex?.let { Crashlytics.logException(ex) }
        }
    }
}