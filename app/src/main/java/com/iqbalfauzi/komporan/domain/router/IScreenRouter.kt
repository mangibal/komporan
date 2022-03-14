package com.iqbalfauzi.komporan.domain.router

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * Created by Iqbal Fauzi at 14/03/22
 * iqbal.fauzi.if99@gmail.com
 */
interface IScreenRouter {

    fun navigateToMainScreen(context: Activity)

    fun getIntentScreen(context: Context, screen: ActivityScreen): Intent

}