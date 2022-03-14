package com.iqbalfauzi.komporan.presentation.main

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iqbalfauzi.komporan.R
import com.iqbalfauzi.komporan.databinding.ActivityMainBinding
import com.iqbalfauzi.komporan.domain.base.BaseActivity
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    ActivityMainBinding::inflate,
    MainViewModel::class
) {

    private val navigationAdapter: MainNavigationAdapter by lazy {
        return@lazy MainNavigationAdapter(this)
    }

    @Suppress("DEPRECATION")
    private val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                selectFragment(0)
                true
            }
            R.id.nav_favorite -> {
                selectFragment(1)
                true
            }
            R.id.nav_notification -> {
                selectFragment(2)
                true
            }
            R.id.nav_profile -> {
                selectFragment(3)
                true
            }
            else -> false
        }
    }

    private fun selectFragment(position: Int) {
        binding.vpContent.setCurrentItem(position, false)
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        with(binding) {
            vpContent.apply {
                offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
                isUserInputEnabled = false
                adapter = navigationAdapter
            }
            navView.setOnItemSelectedListener(navigationListener)
        }
    }

    override fun onInitData() {

    }

}