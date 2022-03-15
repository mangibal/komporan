package com.iqbalfauzi.komporan.presentation.main.post

import android.os.Bundle
import coil.load
import com.iqbalfauzi.data.model.post.PostEntity
import com.iqbalfauzi.external.extensions.*
import com.iqbalfauzi.komporan.R
import com.iqbalfauzi.komporan.databinding.ActivityPostDetailBinding
import com.iqbalfauzi.komporan.domain.base.BaseActivity
import com.iqbalfauzi.komporan.domain.router.ActivityScreen
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class PostDetailActivity : BaseActivity<ActivityPostDetailBinding, PostDetailViewModel>(
    ActivityPostDetailBinding::inflate,
    PostDetailViewModel::class
) {

    private val postEntity: PostEntity by lazy {
        return@lazy dataBundle?.getParcelable(ActivityScreen.PostDetailScreen.POST_DATA_KEY)
            ?: PostEntity()
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding) {
            initToolbar(toolbar)
            setupPostDetail()
        }
    }

    private fun setupPostDetail() {
        with(binding) {
            with(layoutPost) {
                ivUserProfile.load(this.root.context.getDrawableCompat(R.drawable.ironman))
                tvUsername.text = postEntity.userName
                tvUserCompany.text = postEntity.userCompany
                tvPostTitle.text = postEntity.title
                ivShare.onClick {
                    val message = getShareMessageFormat(postEntity.title, postEntity.userName)
                    sharePost(message)
                }
            }
            tvPostBody.text = postEntity.body
        }
    }

    override fun onInitData() {

    }

}