package com.iqbalfauzi.komporan.presentation.main.user

import android.os.Bundle
import coil.load
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
) {

    private val userId: Int by lazy {
        return@lazy dataBundle?.getInt(ActivityScreen.UserDetailScreen.USER_ID_KEY)
            ?: 0
    }

    private lateinit var mUserData: UserData

    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding) {
            initToolbar(toolbar)
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

            getUserDetail(userId)
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
                }
            }
        }
    }

}