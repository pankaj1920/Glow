package com.base.app.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.base.app.listener.IRepositoryListener
import com.base.app.utils.*
import com.base.app.utils.Constants.BaseKeys.Companion.APP
import com.base.app.utils.rxpermissions.RxPermissions
import com.base.app.utils.sharepreference.SharedPrefManager
import com.base.app.viewmodel.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

abstract class BaseActivity<VM : BaseViewModel<*>, VB : ViewDataBinding> : AppCompatActivity() {

    protected abstract val mViewModel: BaseViewModel<*>

    protected lateinit var mViewBinding: VB

    @get:LayoutRes
    abstract val layoutId: Int?

    abstract fun onInitialize()

    abstract fun subscribeObservers()

    private val progressBar: CustomProgressBar? by lazy {
        CustomProgressBar()
    }

    protected val codeSnippet by inject<CodeSnippet>()

    protected val sharedPreferences by inject<SharedPrefManager>()

   /* fun getRxPermissions(): RxPermissions {
        return RxPermissions(this)
    }*/

    protected var mParentView: View? = null


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        //SplitCompat.install(this)

    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        mParentView = window.decorView.findViewById(android.R.id.content)
        return super.onCreateView(name, context, attrs)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this@BaseActivity.layoutId?.let { layoutId ->

            mViewBinding = DataBindingUtil.setContentView(this, layoutId)
        }

        mViewModel.apply {
            isNetworkAvailable = codeSnippet.hasNetworkConnection()
            iRepositoryListener = this@BaseActivity.iRepository
        }

        /**
         * Observer for network connectivity changes
         * */
        NetworkUtils.getNetworkLiveData(this).observe(this, Observer { isConnected ->

            if (mViewModel.isNetworkAvailable != isConnected) {
                onNetworkConnectionChange(isConnected)
                mViewModel.onNetworkConnectionChange(isConnected)
            }

            mViewModel.isNetworkAvailable = isConnected

        })

        lifecycleScope.launch(Dispatchers.Main) {
            async { subscribeObservers() }.await()
            initLoader()
            mViewModel.onInitialized(intent.extras)
            onInitialize()
        }
    }

    private fun initLoader() {
        mViewModel.baseLiveData.observe(this, Observer {
            when (it.first) {
                Constants.BaseKeys.SHOW_LOADER -> runOnUiThread { showProgressBar() }
                Constants.BaseKeys.SHOW_LOADER_MESSAGE -> runOnUiThread { showProgressBar(it.second as? String) }
                Constants.BaseKeys.HIDE_LOADER -> runOnUiThread { dismissProgressBar() }
                Constants.BaseKeys.SHOW_MESSAGE -> runOnUiThread { showMessage(it.second) }
            }
        })

    }

    open fun onNetworkConnectionChange(isConnected: Boolean) {}

    fun navigateTo(
        bundle: Bundle?,
        className: String
    ) {
        val i = Intent()
        i.setClassName(APP, className)

        if (bundle != null)
            i.putExtras(bundle)
        startActivity(i)
    }

    fun navigateToWithResult(
        bundle: Bundle?,
        className: String
    ): Intent {
        val i = Intent()
        i.setClassName(APP, className)

        if (bundle != null)
            i.putExtras(bundle)
        return i
    }

    fun showMessage(message: Any?) {
        if (message is String) {
            Toaster.show(this, message)
        } else if (message is Int) {
            Toaster.show(this, message)
        }

    }

    fun dismissProgressBar() {
        progressBar?.dialog?.let {
            if (it.isShowing)
                it.dismiss()
        }

    }

    fun showProgressBar() {
        dismissProgressBar()
        //Progress Bar with Text
        progressBar?.show(this, null)
    }

    fun showProgressBar(message: String?) {
        dismissProgressBar()
        //Progress Bar with Text
        progressBar?.show(this, message)
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    fun getRepositoryListener(): IRepositoryListener = iRepository

    private val iRepository = object : IRepositoryListener {
        override fun getToken(): String? {
            return sharedPreferences.token
        }

        override fun isNetworkConnected(): Boolean {
            val isConnected = codeSnippet.hasNetworkConnection()
            if (isConnected.not())
                showNetworkMessage()
            return isConnected
        }
//        override fun isNetworkConnected() = true

        override fun unAuthorizedUser() {
            dismissProgressBar()
            sharedPreferences.clearData()
//            navigateNew(Bundle(), Constant.Activity.LOGIN, null)
        }

        override fun showLoader() {
            showProgressBar()
        }

        override fun hideLoader() {
            dismissProgressBar()
        }

        override fun showMessage(message: String) {
            this@BaseActivity.showMessage(message)
        }

        override fun executeOnUiThread(process: () -> Unit?) {
            runOnUiThread {
                process()
            }
        }

    }

    fun navigateNew(bundle: Bundle?, className: String) {
        val i = Intent()
        i.setClassName(APP, className)

        if (bundle != null)
            i.putExtras(bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(i)
        finishAffinity()
    }

    private fun showNetworkMessage() {
        if (mParentView != null) {
            val snackBar = Snackbar.make(mParentView!!, "No Network found!", Snackbar.LENGTH_LONG)
            snackBar.setActionTextColor(Color.RED)
            snackBar.setTextColor(Color.WHITE)
            snackBar.setAction("Settings") { codeSnippet.showNetworkSettings() }
            snackBar.show()
        }
    }

    fun createAlert(layout: Int): Pair<View, androidx.appcompat.app.AlertDialog> {
        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(layout, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return Pair(dialogView, alertDialog)

    }
}
