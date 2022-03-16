package com.iqbalfauzi.komporan.presentation.imageviewer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.koin.core.component.KoinApiExtension

/**
 * Created by Iqbal Fauzi on 3/30/21 11:03 AM
 * iqbal.fauzi.if99@gmail.com
 */
@KoinApiExtension
class ImageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val fragments = mutableListOf<ImageFragment>()

    fun addFragment(fragment: ImageFragment) {
        fragments.add(fragment)
    }

    override fun getItemCount(): Int = fragments.count()

    override fun createFragment(position: Int): Fragment = fragments[position]

}