package com.iqbalfauzi.data.model

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */
sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val ex: Exception) : ApiResponse<Nothing>()
}
