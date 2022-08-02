package com.base.app.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.base.app.listener.IRepositoryListener
import com.base.app.utils.Constants
import com.base.app.utils.SingleLiveEvent
import kotlin.properties.Delegates

abstract class BaseViewModel<S> : ViewModel() {

    var isNetworkAvailable by Delegates.notNull<Boolean>()

    var iRepositoryListener: IRepositoryListener? = null

    abstract fun onInitialized(bundle: Bundle?)

    open fun onNetworkConnectionChange(isConnected: Boolean) {}

    val baseLiveData = SingleLiveEvent<Pair<String, Any>>()

    val stateObserver: SingleLiveEvent<S> by lazy {
        SingleLiveEvent()
    }

    protected fun publishState(state: S) {
        stateObserver.setValue(state)
    }

    protected fun showLoader(){
        baseLiveData.postValue(Pair(Constants.BaseKeys.SHOW_LOADER, ""))
    }

    protected fun hideLoader(){
        baseLiveData.postValue(Pair(Constants.BaseKeys.HIDE_LOADER, ""))
    }

    protected fun showMessage(message: String){
        baseLiveData.postValue(Pair(Constants.BaseKeys.SHOW_MESSAGE, message))
    }
}