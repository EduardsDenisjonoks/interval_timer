package com.exail.intervaltimer.utils

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import timber.log.Timber
import java.lang.Exception

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

@BindingAdapter("is_gone")
fun View.isGone(isGone: Boolean = false) {
    visibility = if (isGone) View.GONE else View.VISIBLE
}

//region NAVIGATION
fun View.navigateTo(@IdRes destination: Int, bundle: Bundle? = null) {
    try {
        Navigation.findNavController(this).navigate(destination, bundle)
    } catch (ex: Exception) {
        Timber.e(ex, "Unable to navigate from this view")
    }
}

fun View.navigateTo(navDirections: NavDirections) {
    try {
        Navigation.findNavController(this).navigate(navDirections)
    } catch (ex: Exception) {
        Timber.e(ex, "Unable to navigate from this view")
    }
}

fun View.navigatePopUpTo(@IdRes destination: Int, inclusive: Boolean = true) {
    try {
        Navigation.findNavController(this).popBackStack(destination, inclusive)
    } catch (ex: Exception) {
        Timber.e(ex, "Unable to pop up to from this view")
    }
}

fun View.navigatePopUp() {
    try {
        Navigation.findNavController(this).popBackStack()
    } catch (ex: Exception) {
        Timber.e(ex, "Unable to pop up from this view")
    }
}


fun Fragment.navigateTo(@IdRes destination: Int) {
    try {
        NavHostFragment.findNavController(this).navigate(destination)
    } catch (ex: Exception) {
        Timber.e(ex, "Unable to navigate from this fragment")
    }
}

fun AppCompatActivity.navigateTo(@IdRes host: Int, @IdRes destination: Int) {
    try {
        Navigation.findNavController(this, host).navigate(destination)
    } catch (ex: Exception) {
        Timber.e(ex, "Unable to navigate from this activity")
    }
}
//endregion