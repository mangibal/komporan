package com.iqbalfauzi.komporan.presentation.main.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.iqbalfauzi.data.model.comment.CommentEntity
import com.iqbalfauzi.data.model.post.PostEntity
import com.iqbalfauzi.external.extensions.getDrawableCompat
import com.iqbalfauzi.external.extensions.onClick
import com.iqbalfauzi.komporan.R
import com.iqbalfauzi.komporan.databinding.ItemCommentBinding
import com.iqbalfauzi.komporan.databinding.ItemPostBinding
import com.iqbalfauzi.komporan.domain.base.BaseAdapter
import com.iqbalfauzi.komporan.domain.base.BaseHolder
import com.iqbalfauzi.komporan.presentation.main.home.PostAdapter

/**
 * Created by Iqbal Fauzi at 14/03/22
 * iqbal.fauzi.if99@gmail.com
 */
class CommentAdapter : BaseAdapter<CommentEntity, ItemCommentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemCommentBinding
        get() = ItemCommentBinding::inflate

    override fun itemViewHolder(binding: ItemCommentBinding): RecyclerView.ViewHolder {
        return CommentViewHolder(binding)
    }

    inner class CommentViewHolder(private val binding: ItemCommentBinding) :
        BaseHolder<CommentEntity>(binding) {
        override fun bind(data: CommentEntity) {
            with(binding) {
                tvAuthorInitial.text = data.name.first().toString()
                tvAuthorName.text = data.name
                tvCommentBody.text = data.body
            }
        }

    }

}