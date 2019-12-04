package com.exail.intervaltimer.utils

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import timber.log.Timber
import java.lang.Exception

/**
 * Simple class to play sounds
 */
class SoundPlayer(private val context: Context) {

    private var currentlyPlaying: Int? = null
    private var mediaPlayer: MediaPlayer? = null

    fun playSound(@RawRes resource: Int) {
        try {
            when {
                resource == -1 -> stop(true)
                currentlyPlaying == resource -> play()
                mediaPlayer != null -> {
                    stop(true)
                    playNewSound(resource)
                }
                else -> playNewSound(resource)

            }
        } catch (ex: Exception) {
            Timber.e(ex, "Unable to plays sound: $resource")
        }
    }

    private fun playNewSound(@RawRes resource: Int){
        mediaPlayer = MediaPlayer.create(context, resource)
        mediaPlayer?.setVolume(100f,100f)
        currentlyPlaying = resource
        play()
    }

    fun stop(release: Boolean = false) {
        mediaPlayer?.stop()
        if (release) {
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    private fun play() {
        if (mediaPlayer?.isPlaying == true){
            mediaPlayer?.pause()
            mediaPlayer?.seekTo(0)
        }
        mediaPlayer?.start()
    }
}