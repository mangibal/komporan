package com.iqbalfauzi.komporan.domain.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Created by Iqbal Fauzi at 14/03/22
 * iqbal.fauzi.if99@gmail.com
 */
abstract class BaseAdapter<T, VB: ViewBinding>: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected lateinit var binding: VB
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    internal var mListItems: MutableList<T> = arrayListOf()
    var onItemClickListener: ((T) -> Unit) = {}

    protected abstract fun itemViewHolder(
        binding: VB
    ): RecyclerView.ViewHolder

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        binding = bindingInflater.invoke(LayoutInflater.from(parent.context), parent, false)
        return itemViewHolder(binding)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as Binder<T>).bind(mListItems[position])
    }

    override fun getItemCount(): Int {
        return mListItems.count()
    }

    fun setItemCallback(callback: (item: T) -> Unit) {
        onItemClickListener = callback
    }

    fun add(item: T) {
        mListItems.add(item)
    }

    fun addAll(data: List<T>) {
        mListItems.run {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    fun filterList(data: List<T>) {
        mListItems.run {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    operator fun get(position: Int): T {
        return mListItems[position]
    }

    interface Binder<T> {
        fun bind(data: T)
    }

}