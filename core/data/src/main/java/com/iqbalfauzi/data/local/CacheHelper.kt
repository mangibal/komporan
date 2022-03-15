package com.iqbalfauzi.data.local

import com.iqbalfauzi.data.model.user.UserData
import com.orhanobut.hawk.Hawk

/**
 * Created by Iqbal Fauzi at 15/03/22
 * iqbal.fauzi.if99@gmail.com
 */
object CacheHelper {
    fun createUsersCache(data: List<UserData>) {
        Hawk.put("userdata", data)
    }

    fun getUsersCache(): List<UserData> {
        return Hawk.get("userdata", emptyList()) ?: emptyList()
    }
}