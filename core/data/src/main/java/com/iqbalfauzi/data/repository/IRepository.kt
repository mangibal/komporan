package com.iqbalfauzi.data.repository

import com.iqbalfauzi.data.model.post.PostEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */
interface IRepository {
    suspend fun getAllPosts(): Flow<DataCallback<List<PostEntity>>>
}