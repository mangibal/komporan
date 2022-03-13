package com.iqbalfauzi.komporan.presentation

import android.os.Bundle
import com.iqbalfauzi.data.model.post.PostEntity
import com.iqbalfauzi.data.repository.DataCallback
import com.iqbalfauzi.external.extensions.logError
import com.iqbalfauzi.external.extensions.logInfo
import com.iqbalfauzi.external.extensions.observe
import com.iqbalfauzi.komporan.databinding.ActivityMainBinding
import com.iqbalfauzi.komporan.domain.base.BaseActivity
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    ActivityMainBinding::inflate,
    MainViewModel::class
) {

    override fun onInitUI(savedInstanceState: Bundle?) {

    }

    override fun onInitData() {
        with(viewModel) {
            observe(allPosts, ::onPostsAvailable)

            getAllPosts()
        }
    }

    private fun onPostsAvailable(data: DataCallback<List<PostEntity>>) {
        when (data) {
            is DataCallback.Loading -> {
                logInfo("Data Loading")
            }
            is DataCallback.Error -> {
                val ex = data.cause
                logError(data.message)
            }
            is DataCallback.Success -> {
                val listPosts = data.value
                logInfo(listPosts.toString())
            }
        }
    }

}