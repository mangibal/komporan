package com.iqbalfauzi.data.repository

import androidx.lifecycle.LiveData
import com.iqbalfauzi.data.model.post.PostEntity
import com.iqbalfauzi.data.model.user.UserData
import kotlinx.coroutines.flow.Flow

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */
interface IRepository {
    suspend fun getAllPosts(): Flow<DataCallback<List<PostEntity>>>
    suspend fun getAllUsers(): Flow<DataCallback<List<UserData>>>
    suspend fun getAllUsersFromCache(): Flow<List<UserData>>
}