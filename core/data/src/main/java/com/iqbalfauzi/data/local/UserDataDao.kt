package com.iqbalfauzi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.iqbalfauzi.data.model.user.UserData
import kotlinx.coroutines.flow.Flow

/**
 * Created by Iqbal Fauzi at 15/03/22
 * iqbal.fauzi.if99@gmail.com
 */
@Dao
interface UserDataDao {

    @Query("SELECT * FROM user_data")
    fun getUserList(): Flow<List<UserData>>

    @Insert(onConflict = REPLACE)
    suspend fun insertAllUser(data: List<UserData>)

}