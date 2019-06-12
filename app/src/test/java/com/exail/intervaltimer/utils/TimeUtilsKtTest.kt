package com.exail.intervaltimer.utils

import org.junit.Test

import org.junit.Assert.*

class TimeUtilsKtTest {

    @Test
    fun test_millisecondsToString() {
        val zero = millisecondsToString(0L)
        val ten = millisecondsToString(10000L)
        val random = millisecondsToString(23410000L)

        assertEquals("00:00:00", zero)
        assertEquals("00:00:10", ten)
        assertEquals("06:30:10", random)
    }

    @Test
    fun test_secondsToString() {
        val zero = secondsToString(0L)
        val five = secondsToString(5L)
        val max = secondsToString(99999L)

        assertEquals("00:00:00", zero)
        assertEquals("00:00:05", five)
        assertEquals("27:46:39", max)
    }

    @Test
    fun test_stringToLong(){
        val nullValue = stringToLong(null)
        val invalidValue = stringToLong("r2r2")
        val validValue = stringToLong("324")

        assertEquals(0L, nullValue)
        assertEquals(0L, invalidValue)
        assertEquals(324L, validValue)
    }
}