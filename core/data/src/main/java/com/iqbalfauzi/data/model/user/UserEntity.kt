package com.iqbalfauzi.data.model.user


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id") val id: Int = 0,
    @ColumnInfo(name = "name")
    @SerializedName("name") val name: String = "",
    @ColumnInfo(name = "username")
    @SerializedName("username") val username: String = "",
    @ColumnInfo(name = "phone")
    @SerializedName("phone") val phone: String = "",
    @ColumnInfo(name = "email")
    @SerializedName("email") val email: String = "",
    @ColumnInfo(name = "website")
    @SerializedName("website") val website: String = "",
    @ColumnInfo(name = "address")
    @SerializedName("address") val address: Address = Address(),
    @ColumnInfo(name = "company")
    @SerializedName("company") val company: Company = Company()
) : Parcelable