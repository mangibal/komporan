package com.iqbalfauzi.data.model.photo


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoEntity(
    @SerializedName("albumId")
    val albumId: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("url")
    val url: String = ""
) : Parcelable