package com.iqbalfauzi.komporan.presentation.main.profile

import android.os.Bundle
import coil.load
import com.iqbalfauzi.external.extensions.*
import com.iqbalfauzi.komporan.R
import com.iqbalfauzi.komporan.databinding.FragmentProfileBinding
import com.iqbalfauzi.komporan.domain.base.BaseFragment
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(
    FragmentProfileBinding::inflate,
    ProfileViewModel::class
) {

    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding) {
            ivUserProfile.loadImage("https://firebasestorage.googleapis.com/v0/b/komporan-40342.appspot.com/o/IMG_20220216_064442.jpg?alt=media&token=066559a6-5bf9-4977-b856-149a63163a46")
            tvUsername.text = String.format("Iqbal Fauzi")
            tvEmail.text = String.format("iqbal.fauzi.if99@gmail.com")
            tvCompany.text = String.format("Komporan")
            tvAddress.text = String.format("Bandung")

            clWa.onClick { requireContext().openWhatsApp("+6287822882668") }
            clIg.onClick { requireContext().openWebPage("https://www.instagram.com/iqbalspace/") }
        }
    }

    override fun onInitData() {

    }

}