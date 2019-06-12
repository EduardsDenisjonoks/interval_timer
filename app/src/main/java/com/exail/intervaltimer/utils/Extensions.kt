package com.exail.intervaltimer.utils

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

/**
 * Created by eduardsdenisjonoks  on 2019-06-11.
 */

/**
 * Set image resource, allows to set null value and will treat values 0 and -1
 * same as null
 */
@BindingAdapter("image_res")
fun AppCompatImageView.setImageRes(@DrawableRes imageRes: Int?){
    when(imageRes){
        null -> setImageDrawable(null)
        in -1..0 -> setImageDrawable(null)
        else -> setImageResource(imageRes)
    }
}