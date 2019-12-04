package com.exail.intervaltimer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.exail.intervaltimer.data.AppCache
import com.exail.intervaltimer.databinding.FragmentIntervalTimerBinding
import com.exail.intervaltimer.utils.navigateTo
import com.exail.intervaltimer.view.model.TimerViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class IntervalTimerFragment : Fragment() {

    private val appCache by inject<AppCache>()
    private val timerViewModel by viewModel<TimerViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentIntervalTimerBinding>(
            inflater,
            R.layout.fragment_interval_timer,
            container,
            false
        )

        initBinding(binding)
        initSettingsButton(binding.btnSettings)
        initMainButton(binding.btnMainAction)
        initResetButton(binding.btnReset)
        initObservers()

        return binding.root
    }

    private fun initBinding(binding: FragmentIntervalTimerBinding) {
        binding.lifecycleOwner = this
        binding.timerVm = timerViewModel
    }

    private fun initSettingsButton(button: AppCompatImageView){
        button.setOnClickListener { v -> v.navigateTo(R.id.action_timer_to_settings) }
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
