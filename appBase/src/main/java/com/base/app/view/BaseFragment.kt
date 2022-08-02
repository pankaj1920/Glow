package com.base.app.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.base.app.utils.CodeSnippet
import com.base.app.utils.Constants
import com.base.app.utils.sharepreference.SharedPrefManager
import com.base.app.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

abstract class BaseFragment<VM : BaseViewModel<*>, VB : ViewDataBinding> : Fragment() {

    protected abstract val mViewModel: BaseViewModel<*>

    lateinit var mViewBinding: VB

    @get:LayoutRes
    abstract val layoutId: Int

    protected val codeSnippet by inject<CodeSnippet>()

    protected val sharedPreferences by inject<SharedPrefManager>()

//    private val progressBar: CustomProgressBar? by lazy {CustomProgressBar()}

    abstract fun subscribeObservers()


    fun showProgressBar() {
        dismissProgressBar()
        (activity as? BaseActivity<*, *>)?.showProgressBar()
    }

    fun showProgressBar(message: String?) {
        dismissProgressBar()
        (activity as? BaseActivity<*, *>)?.showProgressBar(message)
    }

    fun dismissProgressBar() {
        (activity as? BaseActivity<*, *>)?.dismissProgressBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (::mViewBinding.isInitialized.not()) {
            mViewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            mViewBinding.lifecycleOwner = viewLifecycleOwner
        }
        // mViewBinding = activity?.let { DataBindingUtil.setContentView(it, layoutId) }!!
        return mViewBinding.root
    }

    private fun initLoader() {
        mViewModel.baseLiveData.observe(this, Observer {
            when (it.first) {
                Constants.BaseKeys.SHOW_LOADER -> showProgressBar()
                Constants.BaseKeys.SHOW_LOADER_MESSAGE -> showProgressBar(it.second as? String)
                Constants.BaseKeys.HIDE_LOADER -> dismissProgressBar()
                Constants.BaseKeys.SHOW_MESSAGE -> (activity as? BaseActivity<*, *>)?.showMessage(it.second)
            }
        })



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.Main) {
            async { subscribeObservers() }.await()
            mViewModel.iRepositoryListener =
                (activity as? BaseActivity<*, *>)?.getRepositoryListener()
            mViewModel.onInitialized(arguments)
            onFragmentCreated()
            initLoader()
        }
    }

    fun navigateTo(
        bundle: Bundle?,
        className: String
    ) {
        (activity as? BaseActivity<*, *>)?.navigateTo(bundle, className)
    }

    fun navigateToWithResult(
        bundle: Bundle?,
        className: String
    ): Intent? {
        return (activity as? BaseActivity<*, *>)?.navigateToWithResult(bundle, className)
    }

    fun navigateNew(
        bundle: Bundle?,
        className: String
    ) {
        (activity as? BaseActivity<*, *>)?.navigateNew(bundle, className)
    }

    fun showMessage(message: String) {
        (activity as? BaseActivity<*, *>)?.showMessage(message)
    }

    fun createAlert(layout: Int): Pair<View, androidx.appcompat.app.AlertDialog> {
        return (activity as? BaseActivity<*, *>)!!.createAlert(layout)
    }

    abstract fun onFragmentCreated()

}