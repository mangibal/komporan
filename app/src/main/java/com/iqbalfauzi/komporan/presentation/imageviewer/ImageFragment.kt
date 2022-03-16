package com.iqbalfauzi.komporan.presentation.imageviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.load
import com.iqbalfauzi.data.model.photo.PhotoEntity
import com.iqbalfauzi.external.extensions.loadImage
import com.iqbalfauzi.komporan.databinding.ItemFullImageBinding

/**
 * Created by Iqbal Fauzi on 05/07/21 15.47
 * iqbal.fauzi.if99@gmail.com
 */
class ImageFragment : Fragment() {
    private var photoEntity: PhotoEntity = PhotoEntity()

    private lateinit var binding: ItemFullImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemFullImageBinding.inflate(layoutInflater, container, false)

        if (arguments != null) {
            photoEntity = arguments?.getParcelable(PHOTO_DATA_KEY) ?: PhotoEntity()
        }

        binding.ivContent.loadImage(photoEntity.url)
        return binding.root
    }

    companion object {
        const val PHOTO_DATA_KEY = "EXTRA_URI"
        fun newInstance(
            photoEntity: PhotoEntity
        ) = ImageFragment().apply {
            arguments = bundleOf(
                 PHOTO_DATA_KEY to photoEntity
            )
        }
    }
}