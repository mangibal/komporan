package com.iqbalfauzi.external.extensions

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.iqbalfauzi.external.R

/**
 * Created by Iqbal Fauzi at 15/03/22
 * iqbal.fauzi.if99@gmail.com
 */

fun ImageView.loadImage(
    imageUrl: String,
) {
    this.load(imageUrl) {
        crossfade(true)
        placeholder(createCircularProgressDrawable(context))
    }
}

fun createCircularProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).also {
        it.apply {
            setColorSchemeColors(context.getColorCompat(R.color.purple_500))
            strokeWidth = 8f
            centerRadius = 44f
            start()
        }
    }
}

fun createCircularProgressDrawableLight(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).also {
        it.apply {
            setColorSchemeColors(Color.WHITE)
            strokeWidth = 8f
            centerRadius = 44f
            start()
        }
    }
}