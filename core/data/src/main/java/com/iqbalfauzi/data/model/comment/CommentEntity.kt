package com.iqbalfauzi.data.model.comment


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentEntity(
    @SerializedName("body")
    val body: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("postId")
    val postId: Int = 0
) : Parcelable