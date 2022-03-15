package com.iqbalfauzi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iqbalfauzi.data.model.user.UserData

/**
 * Created by Iqbal Fauzi at 15/03/22
 * iqbal.fauzi.if99@gmail.com
 */
@Database(
    exportSchema = true,
    entities = [UserData::class],
    version = 1
)
abstract class KomporanDatabase: RoomDatabase() {
    abstract fun userDataDao(): UserDataDao
}