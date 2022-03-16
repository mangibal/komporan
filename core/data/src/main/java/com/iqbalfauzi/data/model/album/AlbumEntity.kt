package com.iqbalfauzi.data.model.album


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.iqbalfauzi.data.model.photo.PhotoEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumEntity(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("userId")
    val userId: Int = 0,
    var photos: List<PhotoEntity>
) : Parcelable