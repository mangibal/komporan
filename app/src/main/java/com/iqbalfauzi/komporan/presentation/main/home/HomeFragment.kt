package com.iqbalfauzi.komporan.presentation.main.home

import android.os.Bundle
import com.iqbalfauzi.data.model.post.PostEntity
import com.iqbalfauzi.data.model.user.UserData
import com.iqbalfauzi.data.repository.DataCallback
import com.iqbalfauzi.external.extensions.*
import com.iqbalfauzi.komporan.R
import com.iqbalfauzi.komporan.databinding.FragmentHomeBinding
import com.iqbalfauzi.komporan.domain.base.BaseFragment
import org.koin.core.component.KoinApiExtension

/**
 * Created by Iqbal Fauzi at 14/03/22
 * iqbal.fauzi.if99@gmail.com
 */
@KoinApiExtension
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
    HomeViewModel::class
), PostAdapter.OnShareListener {

    private val postAdapter: PostAdapter by lazy {
        return@lazy PostAdapter(this)
    }.also {
        it.value.setItemCallback { item: PostEntity ->
            router.navigateToPostDetailScreen(requireActivity(), item)
        }
    }

    private var mSwipeRefresh = false
    private var mListPost: List<PostEntity> = emptyList()
    private var mListUser: List<UserData> = emptyList()

    override fun onInitUI(savedInstanceState: Bundle?) {
        setupPostListComponent()
        setupSwipeRefresh()
    }

    private fun setupPostListComponent() {
        binding.rvPost.apply {
            requireContext().setupRecyclerViewList(this)
            adapter = postAdapter
        }
    }

    private fun setupSwipeRefresh() {
        binding.srlPost.apply {
            setColorSchemeColors(
                requireContext().getColorCompat(R.color.purple_200),
                requireContext().getColorCompat(R.color.purple_500),
                requireContext().getColorCompat(R.color.purple_700)
            )
            setOnRefreshListener {
                mSwipeRefresh = true
                viewModel.getAllPosts()
            }
        }
    }

    override fun onInitData() {
        with(viewModel) {
            observe(allPosts, ::onPostsAvailable)
            observe(allUsers, ::onUsersAvailable)

            getAllUsers()
        }
    }

    private fun onUsersAvailable(data: List<UserData>) {
        if (data.isNotEmpty()) {
            mListUser = data
            viewModel.getAllPosts()
        }
    }

    private fun onPostsAvailable(data: DataCallback<List<PostEntity>>) {
        with(binding) {
            when (data) {
                is DataCallback.Loading -> {
                    rvPost.goneIf(mListPost.isEmpty())
                    groupLoading.showIf(!mSwipeRefresh)
                }
                is DataCallback.Error -> {
                    rvPost.gone()
                    pbLoading.gone()
                    tvLoading.text = data.message
                }
                is DataCallback.Success -> {
                    groupLoading.gone()
                    rvPost.show()
                    srlPost.isRefreshing = false.also {
                        mSwipeRefresh = it
                    }
                    mListPost = data.value
                    mListPost.forEach { post ->
                        val user = mListUser.find {
                            post.userId == it.id
                        }
                        post.userName = user?.username ?: ""
                        post.userCompany = user?.companyName ?: ""
                    }
                    postAdapter.addAll(mListPost)
                }
            }
        }
    }

    override fun onClickUser(userId: Int) {
        router.navigateToUserDetailScreen(
            requireActivity(),
            userId
        )
    }

    override fun onClickShare(data: PostEntity) {
        val message = getShareMessageFormat(data.title, data.userName)
        requireContext().sharePost(message)
    }

}