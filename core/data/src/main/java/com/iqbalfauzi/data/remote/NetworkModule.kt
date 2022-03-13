package com.iqbalfauzi.data.remote

import com.iqbalfauzi.data.BuildConfig
import com.iqbalfauzi.external.extensions.isDebug
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */
val networkModule = module {

    val networkLogger = HttpLoggingInterceptor().apply {
        level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    single {
        OkHttpClient.Builder().apply {
            addInterceptor(networkLogger)
            readTimeout(60, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

}