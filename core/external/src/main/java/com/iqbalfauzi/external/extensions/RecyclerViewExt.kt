package com.iqbalfauzi.external.extensions

import android.content.Context
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Iqbal Fauzi at 12/03/22
 * iqbal.fauzi.if99@gmail.com
 */
fun Context.setupRecyclerViewList(recyclerView: RecyclerView, isVertical: Boolean = true) {
    recyclerView.apply {
        setHasFixedSize(true)
        setItemViewCacheSize(10)
        layoutManager = if (isVertical) {
            this@setupRecyclerViewList.verticalLayout()
        } else this@setupRecyclerViewList.horizontalLayout()
        itemAnimator = DefaultItemAnimator()
    }
}

fun Context.setupRecyclerViewGrid(recyclerView: RecyclerView, span: Int) {
    recyclerView.apply {
        layoutManager = this@setupRecyclerViewGrid.gridLayout(span)
        setHasFixedSize(true)
        setItemViewCacheSize(10)
        itemAnimator = DefaultItemAnimator()
    }
}

fun Context.gridLayout(span: Int): GridLayoutManager = GridLayoutManager(this, span)

fun Context.verticalLayout(): LinearLayoutManager =
    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

fun Context.horizontalLayout(): LinearLayoutManager =
    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)