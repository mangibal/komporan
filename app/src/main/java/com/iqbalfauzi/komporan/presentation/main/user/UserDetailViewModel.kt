package com.iqbalfauzi.komporan.presentation.main.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iqbalfauzi.data.model.album.AlbumEntity
import com.iqbalfauzi.data.model.photo.PhotoEntity
import com.iqbalfauzi.data.model.user.UserData
import com.iqbalfauzi.data.repository.DataCallback
import com.iqbalfauzi.komporan.domain.base.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension

/**
 * Created by Iqbal Fauzi at 15/03/22
 * iqbal.fauzi.if99@gmail.com
 */
@KoinApiExtension
class UserDetailViewModel : BaseViewModel() {

    private val _userData: MutableLiveData<DataCallback<UserData>> = MutableLiveData()
    val userData: LiveData<DataCallback<UserData>> = _userData

    private val _userAlbums: MutableLiveData<DataCallback<List<AlbumEntity>>> = MutableLiveData()
    val userAlbums: LiveData<DataCallback<List<AlbumEntity>>> = _userAlbums

    fun getUserDetail(userId: Int) {
        viewModelScope.launch {
            repository.getUserDetail(userId)
                .collect {
                    _userData.postValue(it)
                }
        }
    }

    fun getUserAlbums(userId: Int) {
        viewModelScope.launch {
            repository.getUserAlbums(userId)
                .collect {
                    _userAlbums.postValue(it)
                }
        }
    }

    fun getAlbumPhotos(albumId: Int): LiveData<DataCallback<List<PhotoEntity>>> {
        val albumPhotos: MutableLiveData<DataCallback<List<PhotoEntity>>> = MutableLiveData()
        viewModelScope.launch {
            repository.getAlbumPhotos(albumId)
                .collect {
                    albumPhotos.postValue(it)
                }
        }
        return albumPhotos
    }

}