package com.iqbalfauzi.komporan.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.iqbalfauzi.komporan.presentation.main.favorite.FavoriteFragment
import com.iqbalfauzi.komporan.presentation.main.home.HomeFragment
import com.iqbalfauzi.komporan.presentation.main.notification.NotificationFragment
import com.iqbalfauzi.komporan.presentation.main.profile.ProfileFragment
import org.koin.core.component.KoinApiExtension

/**
 * Created by Iqbal Fauzi on 3/30/21 11:03 AM
 * iqbal.fauzi.if99@gmail.com
 */
@KoinApiExtension
class MainNavigationAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val fragments = listOf(
        HomeFragment(),
        FavoriteFragment(),
        NotificationFragment(),
        ProfileFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}