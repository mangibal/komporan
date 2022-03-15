package com.iqbalfauzi.komporan.presentation.main.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iqbalfauzi.data.model.comment.CommentEntity
import com.iqbalfauzi.data.repository.DataCallback
import com.iqbalfauzi.komporan.domain.base.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension

/**
 * Created by Iqbal Fauzi at 15/03/22
 * iqbal.fauzi.if99@gmail.com
 */
@KoinApiExtension
class PostDetailViewModel : BaseViewModel() {

    private val _commentList: MutableLiveData<DataCallback<List<CommentEntity>>> = MutableLiveData()
    val commentList: LiveData<DataCallback<List<CommentEntity>>> = _commentList

    fun getComments(postId: Int) {
        viewModelScope.launch {
            repository.getPostComments(postId)
                .collect {
                    _commentList.postValue(it)
                }
        }
    }

}