package com.iqbalfauzi.external.extensions

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

/**
 * Created by Iqbal Fauzi at 12/03/22
 * iqbal.fauzi.if99@gmail.com
 */
fun AppCompatActivity.initToolbar(toolbar: MaterialToolbar) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = ""
    toolbar.setNavigationOnClickListener { onBackPressed() }
}