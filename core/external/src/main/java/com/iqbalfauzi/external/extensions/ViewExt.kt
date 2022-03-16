package com.iqbalfauzi.external.extensions

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.appbar.MaterialToolbar

/**
 * Created by Iqbal Fauzi at 12/03/22
 * iqbal.fauzi.if99@gmail.com
 */

fun View.margin(
    left: Float? = null,
    top: Float? = null,
    right: Float? = null,
    bottom: Float? = null
) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = dpToPx(this) }
        top?.run { topMargin = dpToPx(this) }
        right?.run { rightMargin = dpToPx(this) }
        bottom?.run { bottomMargin = dpToPx(this) }
    }
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)

fun Context.dpToPx(dp: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

fun View.expand() {
    measure(
        ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
        ConstraintLayout.LayoutParams.WRAP_CONTENT
    )

    val targetHeight: Int = if (0 <= measuredHeight) {
        200
    } else {
        measuredHeight
    }

    layoutParams.height = 0
    show()
    val a = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            layoutParams.height = if (interpolatedTime == 1f)
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            else
                (targetHeight * interpolatedTime).toInt()
            requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    a.duration = (targetHeight / context.resources.displayMetrics.density).toInt().toLong()
    startAnimation(a)
}

fun View.collapse() {
    val initialHeight = measuredHeight

    val a = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            if (interpolatedTime == 1f) {
                gone()
            } else {
                layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                requestLayout()
            }
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    // 1dp/ms
    a.duration = (100).toInt().toLong()
    startAnimation(a)
}

fun View.toggleUpDownWithAnimation(): Boolean {
    return if (rotation == 0f) {
        animate().setDuration(150).rotation(180f)
        true
    } else {
        animate().setDuration(150).rotation(0f)
        false
    }
}

fun AppCompatActivity.initToolbar(toolbar: MaterialToolbar, title: String? = null) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    title?.let { supportActionBar?.title = it }
    toolbar.setNavigationOnClickListener { onBackPressed() }
}

fun AppCompatActivity.setToolbarTitle(title: String) {
    supportActionBar?.title = title
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