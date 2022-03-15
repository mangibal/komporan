package com.iqbalfauzi.komporan.domain.router

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.iqbalfauzi.data.model.post.PostEntity
import com.iqbalfauzi.external.extensions.logError
import com.iqbalfauzi.komporan.domain.base.BaseActivity
import com.iqbalfauzi.komporan.presentation.main.MainActivity
import com.iqbalfauzi.komporan.presentation.splash.SplashActivity
import org.koin.core.component.KoinApiExtension

/**
 * Created by Iqbal Fauzi at 14/03/22
 * iqbal.fauzi.if99@gmail.com
 */
@KoinApiExtension
class ScreenRouter(private val application: Application) : IScreenRouter {

    override fun navigateToMainScreen(context: Activity) {
        val screen = Intent(context, ActivityScreen.MainScreen.INTENT)
        screen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        openActivity(context, screen)
    }

    override fun navigateToPostDetailScreen(context: Activity, postEntity: PostEntity) {
        val screen = Intent(context, ActivityScreen.PostDetailScreen.INTENT)

        openActivity(
            context,
            screen,
            bundleOf(
                ActivityScreen.PostDetailScreen.POST_DATA_KEY to postEntity
            )
        )
    }

    private fun openActivity(
        context: Activity,
        intent: Intent,
        bundle: Bundle? = null,
        isFinish: Boolean = false
    ) {
        try {
            bundle?.let { intent.putExtras(bundle) }
            intent.run { context.startActivity(this) }
            if (isFinish) context.finish()
        } catch (ex: ClassNotFoundException) {
            logError(ex.message.toString())
            sendBroadcastMessage()
        }
    }

    private fun sendBroadcastMessage(message: String = "Screen not found!") {
        val intent = Intent(BaseActivity.ROUTER_MESSAGE)
        intent.putExtra(BaseActivity.MESSAGE, message)
        LocalBroadcastManager.getInstance(application).sendBroadcast(intent)
    }

}