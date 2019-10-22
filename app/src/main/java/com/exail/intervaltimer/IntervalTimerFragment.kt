package com.exail.intervaltimer


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.exail.intervaltimer.R
import com.exail.intervaltimer.databinding.FragmentIntervalTimerBinding
import com.exail.intervaltimer.databinding.FragmentSettingsBinding

/**
 * A simple [Fragment] subclass.
 */
class IntervalTimerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentIntervalTimerBinding>(inflater, R.layout.fragment_interval_timer, container, false)

        return binding.root
    }


}
