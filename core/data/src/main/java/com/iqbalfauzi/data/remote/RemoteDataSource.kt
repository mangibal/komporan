package com.iqbalfauzi.data.remote

import com.iqbalfauzi.data.model.ApiResponse
import com.iqbalfauzi.data.model.DataResponse
import com.iqbalfauzi.data.model.post.PostEntity
import com.iqbalfauzi.external.extensions.logError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */
class RemoteDataSource(
    private val apiService: ApiService
) {

    suspend fun getAllPosts(): Flow<ApiResponse<DataResponse<List<PostEntity>>>> {
        return flow {
            try {
                val response = apiService.getAllPosts()
                emit(ApiResponse.Success(response))
            } catch (ex: Exception) {
                emit(ApiResponse.Error(ex))
                logError("Error Response", ex)
            }
        }
    }

}