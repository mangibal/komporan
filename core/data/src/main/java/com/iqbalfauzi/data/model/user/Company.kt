package com.iqbalfauzi.data.model.user


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Company(
    @SerializedName("bs")
    val bs: String = "",
    @SerializedName("catchPhrase")
    val catchPhrase: String = "",
    @SerializedName("name")
    val name: String = ""
) : Parcelable