package com.iqbalfauzi.komporan.domain.base

import androidx.lifecycle.ViewModel
import com.iqbalfauzi.data.repository.IRepository
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */
@KoinApiExtension
abstract class BaseViewModel: ViewModel(), KoinComponent {

    protected val repository: IRepository by inject()

}