package com.iqbalfauzi.data.model.user


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    @SerializedName("city")
    val city: String = "",
    @SerializedName("geo")
    val geo: Geo = Geo(),
    @SerializedName("street")
    val street: String = "",
    @SerializedName("suite")
    val suite: String = "",
    @SerializedName("zipcode")
    val zipcode: String = ""
): Parcelable