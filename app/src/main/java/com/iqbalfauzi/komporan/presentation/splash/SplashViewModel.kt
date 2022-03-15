package com.iqbalfauzi.komporan.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iqbalfauzi.data.model.post.PostEntity
import com.iqbalfauzi.data.model.user.UserData
import com.iqbalfauzi.data.repository.DataCallback
import com.iqbalfauzi.komporan.domain.base.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */
@KoinApiExtension
class SplashViewModel : BaseViewModel() {

    private val _isSuccess: MutableLiveData<DataCallback<List<UserData>>> = MutableLiveData()
    val isSuccess: LiveData<DataCallback<List<UserData>>> = _isSuccess

    fun getAllUsers() {
        viewModelScope.launch {
            repository.getAllUsers()
                .collect {
                    _isSuccess.postValue(it)
                }
        }
    }

}