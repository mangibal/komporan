package com.iqbalfauzi.data.local

import com.iqbalfauzi.data.model.user.UserData
import kotlinx.coroutines.flow.Flow

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */
class LocalDataSource(
    private val userDataDao: UserDataDao
) {

    fun getAllUsers(): Flow<List<UserData>> {
        return userDataDao.getUserList()
    }

    suspend fun insertAllUser(data: List<UserData>) {
        userDataDao.insertAllUser(data)
    }

}