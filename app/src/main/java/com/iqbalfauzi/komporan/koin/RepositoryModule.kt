package com.iqbalfauzi.komporan.koin

import com.iqbalfauzi.data.remote.RemoteDataSource
import com.iqbalfauzi.data.repository.IRepository
import com.iqbalfauzi.data.repository.Repository
import org.koin.dsl.module

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */
val repositoryModule = module {
    single { RemoteDataSource(apiService = get()) }

    single<IRepository> { Repository(get()) }
}