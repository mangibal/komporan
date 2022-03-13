package com.iqbalfauzi.data.repository

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */
sealed class DataCallback<out T : Any> {
    data class Success<out T : Any>(val value: T) : DataCallback<T>()
    data class Loading<out T: Any>(val data: T? = null) : DataCallback<T>()
    data class Error(val message: String, val cause: Exception? = null) : DataCallback<Nothing>()
}
