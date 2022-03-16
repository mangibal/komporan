package com.iqbalfauzi.komporan.presentation.main.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iqbalfauzi.data.model.photo.PhotoEntity
import com.iqbalfauzi.external.extensions.loadImage
import com.iqbalfauzi.external.extensions.onClick
import com.iqbalfauzi.komporan.databinding.ItemPhotoBinding
import com.iqbalfauzi.komporan.domain.base.BaseAdapter
import com.iqbalfauzi.komporan.domain.base.BaseHolder

/**
 * Created by Iqbal Fauzi at 14/03/22
 * iqbal.fauzi.if99@gmail.com
 */
class PhotoAdapter(private val listener: OnPhotoClickListener) :
    BaseAdapter<PhotoEntity, ItemPhotoBinding>() {

    interface OnPhotoClickListener {
        fun onPhotoClick(position: Int)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemPhotoBinding
        get() = ItemPhotoBinding::inflate

    override fun itemViewHolder(binding: ItemPhotoBinding): RecyclerView.ViewHolder {
        return PhotoViewHolder(binding)
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        BaseHolder<PhotoEntity>(binding) {
        override fun bind(data: PhotoEntity) {
            with(binding) {
                ivPhoto.loadImage(data.thumbnailUrl)
                ivPhoto.onClick {
                    listener.onPhotoClick(adapterPosition)
                }
            }
        }

    }

}