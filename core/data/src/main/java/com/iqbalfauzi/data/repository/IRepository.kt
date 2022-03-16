package com.iqbalfauzi.data.repository

import com.iqbalfauzi.data.model.album.AlbumEntity
import com.iqbalfauzi.data.model.comment.CommentEntity
import com.iqbalfauzi.data.model.photo.PhotoEntity
import com.iqbalfauzi.data.model.post.PostEntity
import com.iqbalfauzi.data.model.user.UserData
import kotlinx.coroutines.flow.Flow

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */
interface IRepository {
    suspend fun getAllPosts(): Flow<DataCallback<List<PostEntity>>>
    suspend fun getPostComments(postId: Int): Flow<DataCallback<List<CommentEntity>>>
    suspend fun getAllUsers(): Flow<DataCallback<List<UserData>>>
    suspend fun getUserDetail(userId: Int): Flow<DataCallback<UserData>>
    suspend fun getUserAlbums(userId: Int): Flow<DataCallback<List<AlbumEntity>>>
    suspend fun getAlbumPhotos(albumId: Int): Flow<DataCallback<List<PhotoEntity>>>
    fun getAllUsersFromCache(): List<UserData>
}