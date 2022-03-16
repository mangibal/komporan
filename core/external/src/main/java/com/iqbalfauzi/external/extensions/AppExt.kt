package com.iqbalfauzi.external.extensions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.iqbalfauzi.external.BuildConfig
import com.iqbalfauzi.external.R

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

fun Context.openWhatsApp(contact: String) {
    val url = "https://api.whatsapp.com/send?phone=$contact"
    try {
        val pm: PackageManager = packageManager
        pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    } catch (e: PackageManager.NameNotFoundException) {
        showToast("Whatsapp app not installed in your phone")
        e.printStackTrace()
    }
}

fun String.toWebUri(): Uri {
    return (if (startsWith("http://") || startsWith("https://")) this else "https://$this").toUri()
}

fun Context.openWebPage(url: String): Boolean {
    // Format the URI properly.
    val uri = url.toWebUri()
    // Try using Chrome Custom Tabs.
    try {
        val intent = CustomTabsIntent.Builder()
            .setToolbarColor(getColorCompat(R.color.purple_500))
            .setShowTitle(true)
            .build()
        intent.launchUrl(this, uri)
        return true
    } catch (ignored: Exception) {
        logError(ignored.message.toString())
    }

    // Fall back to launching a default web browser intent.
    try {
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
            return true
        }
    } catch (ignored: Exception) {
        logError(ignored.message.toString())
    }

    // We were unable to show the web page.
    return false
}

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