package com.iqbalfauzi.komporan.domain.router

/**
 * Created by Iqbal Fauzi at 14/03/22
 * iqbal.fauzi.if99@gmail.com
 */
sealed class ActivityScreen {
    object SplashScreen : ActivityScreen()
    object MainScreen : ActivityScreen()
}
