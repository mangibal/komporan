package com.iqbalfauzi.komporan.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.iqbalfauzi.data.model.post.PostEntity
import com.iqbalfauzi.external.extensions.getDrawableCompat
import com.iqbalfauzi.external.extensions.onClick
import com.iqbalfauzi.komporan.R
import com.iqbalfauzi.komporan.databinding.ItemPostBinding
import com.iqbalfauzi.komporan.domain.base.BaseAdapter
import com.iqbalfauzi.komporan.domain.base.BaseHolder

/**
 * Created by Iqbal Fauzi at 14/03/22
 * iqbal.fauzi.if99@gmail.com
 */
class PostAdapter(private val listener: OnShareListener) :
    BaseAdapter<PostEntity, ItemPostBinding>() {

    interface OnShareListener {
        fun onClickUser(userId: Int)
        fun onClickShare(data: PostEntity)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemPostBinding
        get() = ItemPostBinding::inflate

    override fun itemViewHolder(binding: ItemPostBinding): RecyclerView.ViewHolder {
        return PostViewHolder(binding)
    }

    inner class PostViewHolder(private val binding: ItemPostBinding) :
        BaseHolder<PostEntity>(binding) {
        override fun bind(data: PostEntity) {
            with(binding) {
                ivUserProfile.load(this.root.context.getDrawableCompat(R.drawable.ironman))
                tvUsername.apply {
                    text = data.userName
                    onClick {
                        listener.onClickUser(data.userId)
                    }
                }
                tvUserCompany.text = data.userCompany
                tvPostTitle.text = data.title
                this.root.onClick { onItemClickListener.invoke(data) }
                ivShare.onClick { listener.onClickShare(data) }
            }
        }

    }

}