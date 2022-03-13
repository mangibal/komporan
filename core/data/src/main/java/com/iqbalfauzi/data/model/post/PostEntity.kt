package com.iqbalfauzi.data.model.post

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Iqbal Fauzi at 12/03/22
 * iqbal.fauzi.if99@gmail.com
 */
@Parcelize
data class PostEntity(
    @SerializedName("body") val body: String = "",
    @SerializedName("id") val id: Int = 0,
    @SerializedName("title") val title: String = "",
    @SerializedName("userId") val userId: Int = 0
) : Parcelable