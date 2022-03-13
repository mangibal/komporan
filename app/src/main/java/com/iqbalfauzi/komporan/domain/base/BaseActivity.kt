package com.iqbalfauzi.komporan.domain.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */
abstract class BaseActivity<VB : ViewBinding, VM : ViewModel>(
    private val viewBinder: (LayoutInflater) -> ViewBinding,
    kClass: KClass<VM>
) : AppCompatActivity() {

    @Suppress("UNCHECKED_CAST")
    protected val binding by lazy(LazyThreadSafetyMode.NONE) { viewBinder.invoke(layoutInflater) as VB }
    protected val viewModel: VM by viewModel(clazz = kClass)

    protected var dataBundle: Bundle? = null
    protected var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        dataBundle = intent.extras
        rootView = binding.root
        onInitUI(savedInstanceState)
        onInitData()
    }

    abstract fun onInitUI(savedInstanceState: Bundle?)

    abstract fun onInitData()

}