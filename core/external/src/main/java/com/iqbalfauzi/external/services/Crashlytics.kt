package com.iqbalfauzi.external.services

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.iqbalfauzi.external.extensions.logInfo

/**
 * Created by Iqbal Fauzi at 14/03/22
 * iqbal.fauzi.if99@gmail.com
 */
class Crashlytics {

    private val crashlytics: FirebaseCrashlytics by lazy {
        return@lazy  FirebaseCrashlytics.getInstance()
    }

    fun setLog(message: String) {
        crashlytics.log(message)
    }

    fun recordError(e: Throwable) {
        logInfo("LoginfoError")
        crashlytics.recordException(e)
    }

    fun setKey(key: String, value: String) {
        crashlytics.setCustomKey(key, value)
    }

    fun setKey(key: String, value: Int) {
        crashlytics.setCustomKey(key, value)
    }

    fun setKey(key: String, value: Double) {
        crashlytics.setCustomKey(key, value)
    }

    fun setKey(key: String, value: Float) {
        crashlytics.setCustomKey(key, value)
    }

    fun setKey(key: String, value: Boolean) {
        crashlytics.setCustomKey(key, value)
    }

    fun setKey(key: String, value: Long) {
        crashlytics.setCustomKey(key, value)
    }

}