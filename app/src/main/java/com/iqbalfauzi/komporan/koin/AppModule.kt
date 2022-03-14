package com.iqbalfauzi.komporan.koin

import com.iqbalfauzi.komporan.domain.router.IScreenRouter
import com.iqbalfauzi.komporan.domain.router.ScreenRouter
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module

/**
 * Created by Iqbal Fauzi at 14/03/22
 * iqbal.fauzi.if99@gmail.com
 */
@OptIn(KoinApiExtension::class)
val appModule = module {
   single<IScreenRouter> { ScreenRouter(get()) }
}