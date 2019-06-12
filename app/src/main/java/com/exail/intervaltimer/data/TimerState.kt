package com.exail.intervaltimer.data

import androidx.annotation.IntDef
import com.exail.intervaltimer.data.TimerState.Companion.PAUSE
import com.exail.intervaltimer.data.TimerState.Companion.PLAY
import com.exail.intervaltimer.data.TimerState.Companion.STOP

/**
 * Created by eduardsdenisjonoks  on 2019-06-11.
 */
@IntDef(PLAY, PAUSE, STOP)
@Retention(AnnotationRetention.SOURCE)
annotation class TimerState {
    companion object {
        const val PLAY = 0
        const val PAUSE = 1
        const val STOP = 2
    }
}