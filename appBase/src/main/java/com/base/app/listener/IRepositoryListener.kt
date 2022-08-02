package com.base.app.listener

interface IRepositoryListener {
    fun getToken(): String?
    fun isNetworkConnected(): Boolean
    fun unAuthorizedUser()
    fun showLoader()
    fun hideLoader()
    fun showMessage(message: String)
    fun executeOnUiThread(process: () -> Unit?)
}