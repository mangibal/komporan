package com.iqbalfauzi.komporan.presentation.main.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iqbalfauzi.data.model.album.AlbumEntity
import com.iqbalfauzi.data.model.comment.CommentEntity
import com.iqbalfauzi.data.model.photo.PhotoEntity
import com.iqbalfauzi.external.extensions.*
import com.iqbalfauzi.komporan.databinding.ItemAlbumBinding
import com.iqbalfauzi.komporan.databinding.ItemCommentBinding
import com.iqbalfauzi.komporan.domain.base.BaseAdapter
import com.iqbalfauzi.komporan.domain.base.BaseHolder

/**
 * Created by Iqbal Fauzi at 14/03/22
 * iqbal.fauzi.if99@gmail.com
 */
class AlbumAdapter(private val listener: OnTapPhotoListener) : BaseAdapter<AlbumEntity, ItemAlbumBinding>(),
    PhotoAdapter.OnPhotoClickListener {

    interface OnTapPhotoListener {
        fun onClickPhotos(position: Int, photos: List<PhotoEntity>)
    }

    private var mSelected = 0
    private lateinit var photos: List<PhotoEntity>

    private val photoAdapter: PhotoAdapter by lazy {
        return@lazy PhotoAdapter(this)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemAlbumBinding
        get() = ItemAlbumBinding::inflate

    override fun itemViewHolder(binding: ItemAlbumBinding): RecyclerView.ViewHolder {
        return AlbumViewHolder(binding)
    }

    inner class AlbumViewHolder(private val binding: ItemAlbumBinding) :
        BaseHolder<AlbumEntity>(binding) {
        override fun bind(data: AlbumEntity) {
            with(binding) {
                photos = data.photos
                if (adapterPosition == 0) {
                    root.margin(top = 20F)
                } else {
                    root.margin(top = 10F)
                }

                tvAlbumName.text = data.title

                rvAlbum.apply {
                    this.context.setupRecyclerViewGrid(this, 3)
                    adapter = photoAdapter
                }

                photoAdapter.addAll(data.photos)

                if (mSelected == adapterPosition) {
                    ivMore.toggleUpDownWithAnimation()
                    rvAlbum.expand()
                } else {
                    rvAlbum.collapse()
                }

                root.onClick {
                    mSelected = adapterPosition
                    val show = ivMore.toggleUpDownWithAnimation()
                    if (show) {
                        rvAlbum.expand()
                    } else {
                        rvAlbum.collapse()
                    }
                }
            }
        }

    }

    override fun onPhotoClick(position: Int) {
        listener.onClickPhotos(position, photos)
    }

}