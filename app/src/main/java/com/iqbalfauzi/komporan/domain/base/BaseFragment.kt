package com.iqbalfauzi.komporan.domain.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.iqbalfauzi.komporan.domain.router.IScreenRouter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import kotlin.reflect.KClass

/**
 * Created by Iqbal Fauzi at 13/03/22
 * iqbal.fauzi.if99@gmail.com
 */
@KoinApiExtension
abstract class BaseFragment<VB : ViewBinding, out VM : BaseViewModel>(
    private val viewBinder: (LayoutInflater) -> ViewBinding,
    kClass: KClass<VM>
) : Fragment() {

    @Suppress("UNCHECKED_CAST")
    protected val binding by lazy(LazyThreadSafetyMode.NONE) { viewBinder.invoke(layoutInflater) as VB }
    protected val viewModel: VM by viewModel(clazz = kClass)
    protected val sharedViewModel: VM by viewModel(clazz = kClass)
    protected val router: IScreenRouter by inject()

    protected var dataReceived: Bundle? = null
    protected var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = binding.root
        dataReceived = arguments
        onInitUI(savedInstanceState)
        onInitData()
    }

    abstract fun onInitUI(savedInstanceState: Bundle?)

    abstract fun onInitData()
}