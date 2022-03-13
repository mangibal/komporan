package com.iqbalfauzi.external.extensions

import com.iqbalfauzi.external.BuildConfig

/**
 * Created by Iqbal Fauzi at 12/03/22
 * iqbal.fauzi.if99@gmail.com
 */

val isDebug = BuildConfig.DEBUG

fun isDebug(block: () -> Unit) {
    if (BuildConfig.DEBUG) block.invoke()
}