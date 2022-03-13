package com.iqbalfauzi.data.model.user


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity(
    @SerializedName("address") val address: Address = Address(),
    @SerializedName("company") val company: Company = Company(),
    @SerializedName("email") val email: String = "",
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("phone") val phone: String = "",
    @SerializedName("username") val username: String = "",
    @SerializedName("website") val website: String = ""
) : Parcelable