package com.iqbalfauzi.komporan.domain.router

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.service.autofill.UserData
import com.iqbalfauzi.data.model.photo.PhotoEntity
import com.iqbalfauzi.data.model.post.PostEntity

/**
 * Created by Iqbal Fauzi at 14/03/22
 * iqbal.fauzi.if99@gmail.com
 */
interface IScreenRouter {

    fun navigateToMainScreen(context: Activity)
    fun navigateToPostDetailScreen(context: Activity, postEntity: PostEntity)
    fun navigateToUserDetailScreen(context: Activity, userId: Int)
    fun navigateToImageScreen(context: Activity, photos: List<PhotoEntity>, position: Int)

}