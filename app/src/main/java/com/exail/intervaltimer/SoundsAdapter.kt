package com.exail.intervaltimer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RawRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.exail.intervaltimer.data.AppCache
import com.exail.intervaltimer.data.Sound
import com.exail.intervaltimer.databinding.ItemSoundBinding
import com.exail.intervaltimer.utils.SoundPlayer

class SoundsAdapter(val soundPlayer: SoundPlayer, private val appCache: AppCache) :
    RecyclerView.Adapter<SoundsAdapter.SoundViewHolder>() {

    private val soundList = ArrayList<Sound>()
    private var selectedSound: Int = appCache.getSound()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
        return SoundViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_sound,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = soundList.size

    override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {
        holder.bindView(soundList[position])
    }

    fun populateSoundList(sounds: List<Sound>) {
        soundList.clear()
        if (sounds.isNullOrEmpty()) {
            return
        }
        soundList.addAll(sounds)
        notifyDataSetChanged()
    }

    fun selectSound(@RawRes soundResource: Int) {
        if (selectedSound == soundResource) {
            return
        }
        val indexToClear =
            soundList.indexOf(soundList.firstOrNull { sound -> sound.soundResource == selectedSound })

        selectedSound = soundResource
        appCache.setSound(selectedSound)

        val indexToSelect =
            soundList.indexOf(soundList.firstOrNull { sound -> sound.soundResource == selectedSound })

        notifyItemChanged(indexToClear)
        notifyItemChanged(indexToSelect)
    }

    inner class SoundViewHolder(private val binding: ItemSoundBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnPlay.setOnClickListener {
                soundPlayer.playSound(soundList[adapterPosition].soundResource)
            }
            binding.root.setOnClickListener {
                selectSound(soundList[adapterPosition].soundResource)
            }
        }

        fun bindView(sound: Sound) {
            binding.name = sound.name
            binding.isSelected = selectedSound == sound.soundResource
        }

    }
}