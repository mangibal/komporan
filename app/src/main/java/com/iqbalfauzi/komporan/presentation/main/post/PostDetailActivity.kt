package com.iqbalfauzi.komporan.presentation.main.post

import android.os.Bundle
import com.iqbalfauzi.komporan.databinding.ActivityPostDetailBinding
import com.iqbalfauzi.komporan.domain.base.BaseActivity
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class PostDetailActivity : BaseActivity<ActivityPostDetailBinding, PostDetailViewModel>(
    ActivityPostDetailBinding::inflate,
    PostDetailViewModel::class
) {

    override fun onInitUI(savedInstanceState: Bundle?) {

    }

    override fun onInitData() {

    }

}