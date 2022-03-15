package com.iqbalfauzi.data.local

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Iqbal Fauzi at 15/03/22
 * iqbal.fauzi.if99@gmail.com
 */
val databaseModule = module {

    factory { get<KomporanDatabase>().userDataDao() }

    single {
        Room.databaseBuilder(
            androidContext(),
            KomporanDatabase::class.java,
            "komporan.db"
        ).build()
    }

}