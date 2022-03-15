package com.iqbalfauzi.komporan.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.iqbalfauzi.data.model.post.PostEntity
import com.iqbalfauzi.data.repository.DataCallback
import com.iqbalfauzi.external.extensions.logError
import com.iqbalfauzi.external.extensions.logInfo
import com.iqbalfauzi.external.extensions.observe
import com.iqbalfauzi.external.extensions.showToast
import com.iqbalfauzi.komporan.databinding.ActivitySplashBinding
import com.iqbalfauzi.komporan.domain.base.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinApiExtension

@SuppressLint("CustomSplashScreen")
@KoinApiExtension
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(
    ActivitySplashBinding::inflate,
    SplashViewModel::class
) {

    override fun onInitUI(savedInstanceState: Bundle?) {

    }

    override fun onInitData() {
        with(viewModel) {
            observe(isSuccess) { response ->
                when (response) {
                    is DataCallback.Loading -> {
                        showToast("Please wait, we're populating your data!")
                    }
                    is DataCallback.Error -> {
                        showToast(response.message)
                    }
                    is DataCallback.Success -> {
                        if (response.value.isNotEmpty()) {
                            lifecycleScope.launch(Dispatchers.IO) {
                                delay(1000)
                                withContext(Dispatchers.Main) {
                                    router.navigateToMainScreen(this@SplashActivity)
                                }
                            }
                        } else {
                            showToast("Please wait, we're populating your data!")
                        }
                    }
                }
            }

            getAllUsers()
        }
    }

}