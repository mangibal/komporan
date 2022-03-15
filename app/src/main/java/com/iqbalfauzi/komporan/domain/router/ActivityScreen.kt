package com.iqbalfauzi.komporan.domain.router

import com.iqbalfauzi.komporan.presentation.main.MainActivity
import com.iqbalfauzi.komporan.presentation.main.post.PostDetailActivity
import com.iqbalfauzi.komporan.presentation.splash.SplashActivity
import org.koin.core.component.KoinApiExtension

/**
 * Created by Iqbal Fauzi at 14/03/22
 * iqbal.fauzi.if99@gmail.com
 */
//sealed class ActivityScreen {
//    object SplashScreen : ActivityScreen()
//    object MainScreen : ActivityScreen()
//}

object ActivityScreen {
    @KoinApiExtension
    object SplashScreen {
        val INTENT = SplashActivity::class.java
    }

    @KoinApiExtension
    object MainScreen {
        val INTENT = MainActivity::class.java
    }

    @KoinApiExtension
    object PostDetailScreen {
        val INTENT = PostDetailActivity::class.java
        const val POST_DATA_KEY = "postDataKey"
    }

}

