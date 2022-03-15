package com.iqbalfauzi.data.remote

import com.iqbalfauzi.data.model.DataResponse
import com.iqbalfauzi.data.model.comment.CommentEntity
import com.iqbalfauzi.data.model.photo.PhotoEntity
import com.iqbalfauzi.data.model.post.PostEntity
import com.iqbalfauzi.data.model.user.UserEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Iqbal Fauzi at 12/03/22
 * iqbal.fauzi.if99@gmail.com
 */
interface ApiService {

    @GET("/posts")
    suspend fun getAllPosts(): List<PostEntity>

    @GET("/posts/{postId}")
    suspend fun getPost(@Path("postId") postId: Int): Response<PostEntity>

    @GET("/posts/{postId}/comments")
    suspend fun getPostComments(@Path("postId") postId: Int): List<CommentEntity>

    @GET("/comments")
    suspend fun getAllComments(): Response<DataResponse<List<PostEntity>>>

    @GET("/users")
    suspend fun getAllUsers(): List<UserEntity>

    @GET("/users/{userId}")
    suspend fun getUserDetail(@Path("userId") userId: Int): UserEntity

    @GET("/photos")
    suspend fun getAllPhotos(): Response<DataResponse<List<PhotoEntity>>>

}