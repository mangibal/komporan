package com.iqbalfauzi.data.model.user

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by Iqbal Fauzi at 15/03/22
 * iqbal.fauzi.if99@gmail.com
 */
@Entity(
    tableName = "user_data"
)
@Parcelize
data class UserData(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "username")
    val username: String = "",
    @ColumnInfo(name = "phone")
    val phone: String = "",
    @ColumnInfo(name = "email")
    val email: String = "",
    @ColumnInfo(name = "website")
    val website: String = "",
    @ColumnInfo(name = "city")
    val city: String = "",
    @ColumnInfo(name = "lat")
    val lat: String = "",
    @ColumnInfo(name = "lng")
    val lng: String = "",
    @ColumnInfo(name = "street")
    val street: String = "",
    @ColumnInfo(name = "suite")
    val suite: String = "",
    @ColumnInfo(name = "zipcode")
    val zipcode: String = "",
    @ColumnInfo(name = "bs")
    val bs: String = "",
    @ColumnInfo(name = "catchPhrase")
    val catchPhrase: String = "",
    @ColumnInfo(name = "companyName")
    val companyName: String = ""
) : Parcelable
