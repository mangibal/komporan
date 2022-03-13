package com.iqbalfauzi.komporan.koin

import com.iqbalfauzi.komporan.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */

@OptIn(KoinApiExtension::class)
val viewModelModule = module {
    viewModel { MainViewModel() }
}