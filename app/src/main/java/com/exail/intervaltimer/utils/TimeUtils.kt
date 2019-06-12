package com.exail.intervaltimer.utils

import java.lang.Exception
import java.util.*

/**
 * Created by eduardsdenisjonoks  on 2019-06-11.
 */

/**
 * Convert long milliseconds value into string
 */
fun millisecondsToString(milliseconds: Long) = secondsToString(milliseconds / 1000)

/**
 * Convert long seconds value into string
 */
fun secondsToString(secondsIn: Long): String {
    val seconds = secondsIn % 60
    val minutes = secondsIn / 60 % 60
    val hours = secondsIn / 60 / 60 % 60

    return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds)
}

/**
 * Convert string to long
 */
fun stringToLong(string: String?): Long{
    if (string.isNullOrBlank()){
        return 0L
    }
    return try { string.toLong() } catch (ex: Exception) { 0L }
}