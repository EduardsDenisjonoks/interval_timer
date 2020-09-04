package com.exail.intervaltimer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exail.intervaltimer.data.AppCache
import com.exail.intervaltimer.data.Sound
import com.exail.intervaltimer.databinding.FragmentSettingsBinding
import com.exail.intervaltimer.utils.SoundPlayer
import org.koin.android.ext.android.inject
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {

    private val appCache by inject<AppCache>()
    private val soundPlayer by inject<SoundPlayer>()
    private val soundsAdapter by lazy { SoundsAdapter(soundPlayer, appCache) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSettingsBinding>(
            inflater,
            R.layout.fragment_settings,
            container,
            false
        )

        initAppModeButton(binding.btnAppMode)
        initAppModeLabel(binding.appThemeValue)
        initVibrationSwitch(binding)
        initSoundList(binding.soundList)
        populateData()

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        soundPlayer.stop(true)
    }

    private fun initAppModeButton(button: AppCompatImageView) {
        button.setOnClickListener {
            if (appCache.getAppMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                appCache.selectAppMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                appCache.selectAppMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            requireActivity().recreate()
        }

    }

    private fun initAppModeLabel(labelView: AppCompatTextView) {
        val modeString = when (appCache.getAppMode()) {
            AppCompatDelegate.MODE_NIGHT_YES -> context?.getString(R.string.value_app_mode_night)
            else -> context?.getString(R.string.value_app_mode_day)
        }

        labelView.text = String.format(
            Locale.getDefault(),
            context?.getString(R.string.settings_app_mode) ?: "%s",
            modeString
        )
    }

    private fun initVibrationSwitch(binding: FragmentSettingsBinding) {
        val switch = binding.vibrationSwitch
        val labelView = binding.vibrationValue

        switch.isChecked = appCache.getVibration()
        setVibrationLabel(labelView)
        switch.setOnCheckedChangeListener { _, isChecked ->
            run {
                appCache.setVibration(isChecked)
                setVibrationLabel(labelView)
            }
        }
    }

    private fun setVibrationLabel(labelView: AppCompatTextView) {
        val vibrationState = when (appCache.getVibration()) {
            true -> context?.getString(R.string.value_on)
            else -> context?.getString(R.string.value_off)
        }

        labelView.text = String.format(
            Locale.getDefault(),
            context?.getString(R.string.settings_vibration) ?: "%s",
            vibrationState
        )
    }

    private fun initSoundList(listView: RecyclerView) {
        listView.adapter = soundsAdapter
        listView.layoutManager = LinearLayoutManager(context)
        listView.setHasFixedSize(true)
    }

    private fun populateData() {
        soundsAdapter.populateSoundList(Sound.soundList())
    }

}
