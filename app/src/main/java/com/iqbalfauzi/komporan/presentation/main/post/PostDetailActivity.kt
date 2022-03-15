package com.iqbalfauzi.komporan.presentation.main.post

import android.os.Bundle
import coil.load
import com.iqbalfauzi.data.model.comment.CommentEntity
import com.iqbalfauzi.data.model.post.PostEntity
import com.iqbalfauzi.data.repository.DataCallback
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

    private val commentAdapter: CommentAdapter by lazy {
        return@lazy CommentAdapter()
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding) {
            initToolbar(toolbar)
            setupPostDetail()
            setupListComment()
        }
    }

    private fun setupListComment() {
        with(binding) {
            rvComment.apply {
                setupRecyclerViewList(this)
                adapter = commentAdapter
            }
        }
    }

    private fun setupPostDetail() {
        with(binding) {
            with(layoutPost) {
                ivUserProfile.load(this.root.context.getDrawableCompat(R.drawable.ironman))
                tvUsername.text = postEntity.userName
                tvUserCompany.text = postEntity.userCompany
                tvPostTitle.text = postEntity.title
                root.background = null
                ivShare.onClick {
                    val message = getShareMessageFormat(postEntity.title, postEntity.userName)
                    sharePost(message)
                }
                viewBottom.gone()
            }
            tvPostBody.text = postEntity.body
        }
    }

    override fun onInitData() {
        with(viewModel) {
            observe(commentList, ::onCommentsAvailable)

            getComments(postEntity.id)
        }
    }

    private fun onCommentsAvailable(data: DataCallback<List<CommentEntity>>) {
        with(binding) {
            when (data) {
                is DataCallback.Loading -> {
                    groupLoading.show()
                }
                is DataCallback.Error -> {
                    pbLoading.gone()
                    tvLoading.text = data.message
                }
                is DataCallback.Success -> {
                    groupLoading.gone()
                    tvLoading.apply {
                        showIf(data.value.isEmpty())
                        text = getString(R.string.msg_empty_comments)
                    }
                    rvComment.showIf(data.value.isNotEmpty())
                    tvCommentsTitle.text =
                        String.format(
                            "%s ${getString(R.string.title_comments)}",
                            data.value.count()
                        )
                    commentAdapter.addAll(data.value)
                }
            }
        }
    }

}