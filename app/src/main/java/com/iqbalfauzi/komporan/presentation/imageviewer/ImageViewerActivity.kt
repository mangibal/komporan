package com.iqbalfauzi.komporan.presentation.imageviewer

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.iqbalfauzi.data.model.photo.PhotoEntity
import com.iqbalfauzi.external.extensions.initToolbar
import com.iqbalfauzi.external.extensions.setToolbarTitle
import com.iqbalfauzi.komporan.databinding.ActivityImageViewerBinding
import com.iqbalfauzi.komporan.domain.base.BaseActivity
import com.iqbalfauzi.komporan.domain.router.ActivityScreen
import com.iqbalfauzi.komporan.presentation.main.MainViewModel
import org.koin.core.component.KoinApiExtension

/**
 * Created by Iqbal Fauzi on 05/07/21 15.56
 * iqbal.fauzi.if99@gmail.com
 */
@KoinApiExtension
class ImageViewerActivity : BaseActivity<ActivityImageViewerBinding, MainViewModel>(
    ActivityImageViewerBinding::inflate,
    MainViewModel::class
) {

    private val imageAdapter: ImageAdapter by lazy {
        return@lazy ImageAdapter(this)
    }

    private val photos: List<PhotoEntity> by lazy {
        return@lazy dataBundle?.getParcelableArrayList<PhotoEntity>(ActivityScreen.ImageViewerScreen.PHOTOS_DATA_KEY)
            ?: emptyList()
    }

    private val position: Int by lazy {
        return@lazy dataBundle?.getInt(ActivityScreen.ImageViewerScreen.POSITION_KEY) ?: 0
    }

    private var pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            this@ImageViewerActivity.setToolbarTitle(photos[position].title)
        }
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding) {
            initToolbar(toolbar)
            setupImageAdapter()
        }
    }

    private fun setupImageAdapter() {
        with(binding) {
            viewPager.apply {
                offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
                isUserInputEnabled = true
                adapter = imageAdapter
                registerOnPageChangeCallback(pageChangeCallback)
            }
            photos.forEach {
                val fragment = ImageFragment.newInstance(it)
                imageAdapter.addFragment(fragment)
            }
            viewPager.setCurrentItem(position, false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewPager.unregisterOnPageChangeCallback(pageChangeCallback)
    }

    override fun onInitData() {

    }
}