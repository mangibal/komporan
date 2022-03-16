package com.iqbalfauzi.data.remote

import com.iqbalfauzi.data.model.ApiResponse
import com.iqbalfauzi.data.model.album.AlbumEntity
import com.iqbalfauzi.data.model.comment.CommentEntity
import com.iqbalfauzi.data.model.photo.PhotoEntity
import com.iqbalfauzi.data.model.post.PostEntity
import com.iqbalfauzi.data.model.user.UserEntity
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

    suspend fun getAllUsers(): Flow<ApiResponse<List<UserEntity>>> {
        return flow {
            try {
                val response = apiService.getAllUsers()
                emit(ApiResponse.Success(response))
            } catch (ex: Exception) {
                emit(ApiResponse.Error(ex))
                logError("Error Response", ex)
            }
        }
    }

    suspend fun getUserDetail(userId: Int): Flow<ApiResponse<UserEntity>> {
        return flow {
            try {
                val response = apiService.getUserDetail(userId)
                emit(ApiResponse.Success(response))
            } catch (ex: Exception) {
                emit(ApiResponse.Error(ex))
                logError("Error Response", ex)
            }
        }
    }

    suspend fun getUserAlbums(userId: Int): Flow<ApiResponse<List<AlbumEntity>>> {
        return flow {
            try {
                val response = apiService.getUserAlbums(userId)
                emit(ApiResponse.Success(response))
            } catch (ex: Exception) {
                emit(ApiResponse.Error(ex))
                logError("Error Response", ex)
            }
        }
    }

    suspend fun getAlbumPhotos(albumId: Int): Flow<ApiResponse<List<PhotoEntity>>> {
        return flow {
            try {
                val response = apiService.getAlbumPhotos(albumId)
                emit(ApiResponse.Success(response))
            } catch (ex: Exception) {
                emit(ApiResponse.Error(ex))
                logError("Error Response", ex)
            }
        }
    }

    suspend fun getPostComments(postId: Int): Flow<ApiResponse<List<CommentEntity>>> {
        return flow {
            try {
                val response = apiService.getPostComments(postId)
                emit(ApiResponse.Success(response))
            } catch (ex: Exception) {
                emit(ApiResponse.Error(ex))
                logError("Error Response", ex)
            }
        }
    }

    suspend fun getAllPosts(): Flow<ApiResponse<List<PostEntity>>> {
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