package com.iqbalfauzi.komporan

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.iqbalfauzi.data.remote.networkModule
import com.iqbalfauzi.external.extensions.timberInit
import com.iqbalfauzi.komporan.koin.repositoryModule
import com.iqbalfauzi.komporan.koin.viewModelModule
import com.orhanobut.hawk.Hawk
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */
class KomporanApp : Application() {

    override fun onCreate() {
        super.onCreate()
        disableDarkMode()
        initLogger()
        initHawk()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@KomporanApp)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun initHawk() {
        Hawk.init(this).build()
    }

    private fun initLogger() {
        timberInit()
        Logger.addLogAdapter(AndroidLogAdapter())
    }

}