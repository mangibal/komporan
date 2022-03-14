package com.iqbalfauzi.komporan.domain.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Created by Iqbal Fauzi at 14/03/22
 * iqbal.fauzi.if99@gmail.com
 */
abstract class BaseHolder<T>(binder: ViewBinding) : RecyclerView.ViewHolder(binder.root),
    BaseAdapter.Binder<T>