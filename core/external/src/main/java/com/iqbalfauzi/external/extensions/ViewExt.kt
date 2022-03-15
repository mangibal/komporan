package com.iqbalfauzi.external.extensions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

/**
 * Created by Iqbal Fauzi at 12/03/22
 * iqbal.fauzi.if99@gmail.com
 */
fun AppCompatActivity.initToolbar(toolbar: MaterialToolbar) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
//    supportActionBar?.title = ""
    toolbar.setNavigationOnClickListener { onBackPressed() }
}

fun <T : View> T.onClick(block: () -> Unit) = setOnClickListener { block() }
fun <T : View> T.onLongClick(block: () -> Boolean) = setOnLongClickListener { block() }

fun View.gone() {
    visibility = View.GONE
}

fun View.goneIf(isShow: Boolean = true) {
    visibility = if (isShow) View.GONE else View.VISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.showIf(isShow: Boolean = true) {
    visibility = if (isShow) View.VISIBLE else View.GONE
}