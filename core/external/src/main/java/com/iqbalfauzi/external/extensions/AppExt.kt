package com.iqbalfauzi.external.extensions

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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

fun getShareMessageFormat(
    title: String,
    userName: String
) = String.format("""
    %s
    
    created by %s
    Kumparan
""".trimIndent(), title, userName)

fun Context.sharePost(message: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, message)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun Context?.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    this?.let { Toast.makeText(it, text, duration).show() }

fun Context?.showToast(@StringRes textId: Int, duration: Int = Toast.LENGTH_SHORT) =
    this?.let { Toast.makeText(it, textId, duration).show() }