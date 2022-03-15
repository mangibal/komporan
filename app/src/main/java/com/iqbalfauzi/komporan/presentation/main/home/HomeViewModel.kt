package com.iqbalfauzi.komporan.presentation.main.home

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
class HomeViewModel : BaseViewModel() {

    private val _allPosts: MutableLiveData<DataCallback<List<PostEntity>>> = MutableLiveData()
    val allPosts: LiveData<DataCallback<List<PostEntity>>> = _allPosts

    private val _allUsers: MutableLiveData<List<UserData>> = MutableLiveData()
    val allUsers: LiveData<List<UserData>> = _allUsers

    fun getAllUsers() {
        viewModelScope.launch {
            _allUsers.postValue(repository.getAllUsersFromCache())
        }
    }

    fun getAllPosts() {
        viewModelScope.launch {
            repository.getAllPosts()
                .collect {
                    _allPosts.postValue(it)
                }
        }
    }

}