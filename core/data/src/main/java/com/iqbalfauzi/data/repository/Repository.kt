package com.iqbalfauzi.data.repository

import com.iqbalfauzi.data.local.CacheHelper
import com.iqbalfauzi.data.local.LocalDataSource
import com.iqbalfauzi.data.mapper.toSingleEntity
import com.iqbalfauzi.data.model.ApiResponse
import com.iqbalfauzi.data.model.comment.CommentEntity
import com.iqbalfauzi.data.model.post.PostEntity
import com.iqbalfauzi.data.model.user.UserData
import com.iqbalfauzi.data.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */
class Repository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IRepository {

    override suspend fun getAllPosts(): Flow<DataCallback<List<PostEntity>>> {
        return flow {
            try {
                emit(DataCallback.Loading())
                remoteDataSource.getAllPosts()
                    .flowOn(Dispatchers.IO)
                    .collect { apiResponse ->
                        when (apiResponse) {
                            is ApiResponse.Success -> {
                                val data = apiResponse.data
                                if (data.isNotEmpty()) {
                                    emit(DataCallback.Success(data))
                                } else {
                                    emit(DataCallback.Error("Data is Empty"))
                                }
                            }
                            is ApiResponse.Error -> {
                                emit(
                                    DataCallback.Error(
                                        apiResponse.ex.message.toString(),
                                        apiResponse.ex
                                    )
                                )
                            }
                        }
                    }
            } catch (ex: Exception) {
                emit(DataCallback.Error(ex.message.toString(), ex))
            }
        }
    }

    override suspend fun getPostComments(postId: Int): Flow<DataCallback<List<CommentEntity>>> {
        return flow {
            try {
                emit(DataCallback.Loading())
                remoteDataSource.getPostComments(postId)
                    .flowOn(Dispatchers.IO)
                    .collect { apiResponse ->
                        when (apiResponse) {
                            is ApiResponse.Success -> {
                                val data = apiResponse.data
                                if (data.isNotEmpty()) {
                                    emit(DataCallback.Success(data))
                                } else {
                                    emit(DataCallback.Error("Data is Empty"))
                                }
                            }
                            is ApiResponse.Error -> {
                                emit(
                                    DataCallback.Error(
                                        apiResponse.ex.message.toString(),
                                        apiResponse.ex
                                    )
                                )
                            }
                        }
                    }
            } catch (ex: Exception) {
                emit(DataCallback.Error(ex.message.toString(), ex))
            }
        }
    }

    override suspend fun getAllUsers(): Flow<DataCallback<List<UserData>>> {
        return flow {
            try {
                emit(DataCallback.Loading())

                val cache = CacheHelper.getUsersCache()
                if (cache.isNotEmpty()) {
                    emit(DataCallback.Success(cache))
                }

                remoteDataSource.getAllUsers()
                    .flowOn(Dispatchers.IO)
                    .collect { apiResponse ->
                        when (apiResponse) {

                            is ApiResponse.Success -> {
                                val data = apiResponse.data.map {
                                    it.toSingleEntity()
                                }

                                if (data.isNotEmpty()) {
                                    if (data != cache) {
                                        CacheHelper.createUsersCache(data)
                                        emit(DataCallback.Success(data))
                                    }
                                } else {
                                    emit(DataCallback.Error("Data is Empty"))
                                }
                            }

                            is ApiResponse.Error -> {
                                emit(
                                    DataCallback.Error(
                                        apiResponse.ex.message.toString(),
                                        apiResponse.ex
                                    )
                                )
                            }
                        }
                    }
            } catch (ex: Exception) {
                emit(DataCallback.Error(ex.message.toString(), ex))
            }
        }
    }

    override fun getAllUsersFromCache(): List<UserData> {
        return CacheHelper.getUsersCache()
    }


}