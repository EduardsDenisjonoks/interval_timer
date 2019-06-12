package com.exail.intervaltimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import com.exail.intervaltimer.data.AppCache
import com.exail.intervaltimer.databinding.ActivityIntervalTimerBinding
import org.koin.android.ext.android.inject
import com.exail.intervaltimer.view.model.TimerViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class IntervalTimerActivity : AppCompatActivity() {

    private val appCache: AppCache by inject()
    private val timerViewModel: TimerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAppMode()

        val binding = DataBindingUtil.setContentView<ActivityIntervalTimerBinding>(
            this,
            R.layout.activity_interval_timer
        )

        initBinding(binding)
        initAppModeButton(binding.btnAppMode)
        initMainButton(binding.btnMainAction)
        initResetButton(binding.btnReset)
        initObservers()

    }

    private fun initAppMode() {
        if (appCache.getAppMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun initBinding(binding: ActivityIntervalTimerBinding) {
        binding.lifecycleOwner = this
        binding.timerVm = timerViewModel
    }

    private fun initAppModeButton(button: AppCompatImageView) {
        button.setOnClickListener {
            if (appCache.getAppMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                appCache.selectAppMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                appCache.selectAppMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            recreate()
        }
    }

    private fun initMainButton(button: AppCompatImageView) {
        button.setOnClickListener { timerViewModel.toggleStartPause() }
    }

    private fun initResetButton(button: AppCompatImageView) {
        button.setOnClickListener { timerViewModel.reset() }
    }

    private fun initObservers() {
        timerViewModel.interval.observe(this, androidx.lifecycle.Observer { interval ->
            appCache.setInterval(if (interval.isNullOrBlank()) 0L else interval.toLong())
        })
    }
}
