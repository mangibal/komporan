package com.iqbalfauzi.komporan.presentation.main.user

import android.os.Bundle
import coil.load
import com.iqbalfauzi.data.model.album.AlbumEntity
import com.iqbalfauzi.data.model.photo.PhotoEntity
import com.iqbalfauzi.data.model.user.UserData
import com.iqbalfauzi.data.repository.DataCallback
import com.iqbalfauzi.external.extensions.*
import com.iqbalfauzi.komporan.R
import com.iqbalfauzi.komporan.databinding.ActivityUserDetailBinding
import com.iqbalfauzi.komporan.domain.base.BaseActivity
import com.iqbalfauzi.komporan.domain.router.ActivityScreen
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class UserDetailActivity : BaseActivity<ActivityUserDetailBinding, UserDetailViewModel>(
    ActivityUserDetailBinding::inflate,
    UserDetailViewModel::class
), AlbumAdapter.OnTapPhotoListener {

    private val userId: Int by lazy {
        return@lazy dataBundle?.getInt(ActivityScreen.UserDetailScreen.USER_ID_KEY)
            ?: 0
    }

    private val albumAdapter: AlbumAdapter by lazy {
        return@lazy AlbumAdapter(this)
    }

    private lateinit var mUserData: UserData
    private lateinit var mUserAlbums: List<AlbumEntity>

    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding) {
            initToolbar(toolbar)
            setupAlbumList()
        }
    }

    private fun setupAlbumList() {
        binding.rvAlbum.apply {
            setupRecyclerViewList(this)
            adapter = albumAdapter
        }
    }

    private fun setupUserProfile() {
        with(binding) {
            ivUserProfile.load(this.root.context.getDrawableCompat(R.drawable.ironman))
            tvUsername.text = mUserData.username
            tvEmail.text = mUserData.email
            tvCompany.text = mUserData.companyName
            tvAddress.text = String.format(
                "%s Street, %s, %s City",
                mUserData.street,
                mUserData.suite,
                mUserData.city
            )
        }
    }

    override fun onInitData() {
        with(viewModel) {
            observe(userData, ::onUserDataAvailable)
            observe(userAlbums, ::onUserAlbumsAvailable)

            getUserDetail(userId)
        }
    }

    private fun onUserAlbumsAvailable(data: DataCallback<List<AlbumEntity>>) {
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
                    mUserAlbums = data.value
                    mUserAlbums.forEach { album ->
                        observe(viewModel.getAlbumPhotos(album.id)) { items ->
                            when (items) {
                                is DataCallback.Loading -> {}
                                is DataCallback.Error -> {}
                                is DataCallback.Success -> {
                                    album.photos = items.value
                                    albumAdapter.add(album)
                                }
                            }
                        }
                    }
                    groupLoading.gone()
                    rvAlbum.show()
                }
            }
        }
    }

    private fun onUserDataAvailable(data: DataCallback<UserData>) {
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
                    mUserData = data.value
                    setToolbarTitle(mUserData.username)
                    setupUserProfile()
                    viewModel.getUserAlbums(userId)
                }
            }
        }
    }

    override fun onClickPhotos(position: Int, photos: List<PhotoEntity>) {
        router.navigateToImageScreen(this, photos, position)
    }

}