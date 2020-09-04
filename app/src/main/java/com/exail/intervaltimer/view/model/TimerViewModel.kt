package com.exail.intervaltimer.view.model

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.exail.intervaltimer.R
import com.exail.intervaltimer.data.AppCache
import com.exail.intervaltimer.data.TimerState
import com.exail.intervaltimer.utils.SoundPlayer
import com.exail.intervaltimer.utils.secondsToString
import com.exail.intervaltimer.utils.stringToLong
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

/**
 * Created by eduardsdenisjonoks  on 2019-06-07.
 */
class TimerViewModel constructor(
    private val cache: AppCache,
    private val soundPlayer: SoundPlayer,
    private val vibrator: Vibrator
) : ViewModel() {

    private var state = TimerState.STOP

    private val elapsedSeconds = MutableLiveData<Long>().apply { value = 0L }
    private val scheduler = Executors.newSingleThreadScheduledExecutor()
    private val tickRunnable = Runnable { onTick() }
    private var scheduledFuture: ScheduledFuture<*>? = null

    val icon = MutableLiveData<Int>().apply { value = R.drawable.ic_play }
    val interval = MutableLiveData<String>().apply { value = cache.getInterval().toString() }
    val time: LiveData<String> =
        Transformations.map(elapsedSeconds) { seconds -> secondsToString(seconds) }

    //region CONTROLS
    /**
     * Toggle start pause state
     */
    fun toggleStartPause() {
        when (state) {
            TimerState.PLAY -> pause()
            TimerState.PAUSE -> start()
            TimerState.STOP -> start()
        }
    }

    /**
     * Start timer
     */
    private fun start() {
        state = TimerState.PLAY
        updateIcon(state)
        scheduledFuture = scheduler.scheduleAtFixedRate(tickRunnable, 0, 1, TimeUnit.SECONDS)
    }

    /**
     * Pause timer
     */
    private fun pause() {
        state = TimerState.PAUSE
        updateIcon(state)
        soundPlayer.stop()
        scheduledFuture?.cancel(true)
        scheduledFuture = null
    }

    /**
     * Stop and reset timer
     */
    fun reset() {
        pause()
        state = TimerState.STOP
        elapsedSeconds.value = 0L
    }

    /**
     * Update main button icon, if playing show pause else show play
     */
    private fun updateIcon(state: Int) {
        when (state) {
            TimerState.PLAY -> icon.value = R.drawable.ic_pause
            else -> icon.value = R.drawable.ic_play
        }
    }
    //endregion

    //region TICK
    /**
     * On timer tick add second and play sound if matches requirement
     */
    private fun onTick() {
        val latestTime = addSecond()
        if (!isInterval(latestTime, stringToLong(interval.value))) {
            return
        }
        playSound()
        vibrate()
    }

    /**
     * Add second to elapsed time
     */
    private fun addSecond(): Long {
        val addedSecond = elapsedSeconds.value?.plus(1) ?: 0L
        elapsedSeconds.postValue(addedSecond)
        return addedSecond
    }

    /**
     * Check if it is interval and notification should be given
     */
    private fun isInterval(seconds: Long, interval: Long) =
        interval != 0L && seconds.rem(interval) == 0L

    /**
     * Play sound if matching interval
     */
    private fun playSound() {
        soundPlayer.playSound(cache.getSound())
    }

    /**
     * Vibrate if enabled
     */
    @Suppress("DEPRECATION")
    private fun vibrate() {
        if (!cache.getVibration()) {
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {//Deprecated since 26
            vibrator.vibrate(500)
        }
    }
    //endregion


    /**
     * Cancel timer and release tone generator
     */
    override fun onCleared() {
        super.onCleared()
        scheduledFuture?.cancel(true)
        soundPlayer.stop(true)
    }
}