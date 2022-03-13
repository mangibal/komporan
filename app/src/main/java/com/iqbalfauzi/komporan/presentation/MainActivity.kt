package com.iqbalfauzi.komporan.presentation

import android.os.Bundle
import com.iqbalfauzi.komporan.databinding.ActivityMainBinding
import com.iqbalfauzi.komporan.domain.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    ActivityMainBinding::inflate,
    MainViewModel::class
) {

    override fun onInitUI(savedInstanceState: Bundle?) {

    }

    override fun onInitData() {

    }

}