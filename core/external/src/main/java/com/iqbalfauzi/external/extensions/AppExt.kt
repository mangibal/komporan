package com.iqbalfauzi.external.extensions

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.iqbalfauzi.external.BuildConfig

/**
 * Created by Iqbal Fauzi at 12/03/22
 * iqbal.fauzi.if99@gmail.com
 */

val isDebug = BuildConfig.DEBUG

fun isDebug(block: () -> Unit) {
    if (BuildConfig.DEBUG) block.invoke()
}

fun Context.getDrawableCompat(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)
fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)
fun Context.getColor(@ColorRes id: Int) = ContextCompat.getColor(this, id)