package com.iqbalfauzi.komporan.koin

import com.iqbalfauzi.komporan.presentation.main.MainViewModel
import com.iqbalfauzi.komporan.presentation.main.home.HomeViewModel
import com.iqbalfauzi.komporan.presentation.main.post.PostDetailViewModel
import com.iqbalfauzi.komporan.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */

@OptIn(KoinApiExtension::class)
val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }
    viewModel { HomeViewModel() }
    viewModel { PostDetailViewModel() }
}