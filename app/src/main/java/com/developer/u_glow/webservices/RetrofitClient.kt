package com.developer.u_glow.webservices

import com.base.app.listener.IRepositoryListener
import com.base.app.webservice.BaseRetrofitClient
import com.base.app.webservice.Feature

class RetrofitClient(listener: IRepositoryListener?) : BaseRetrofitClient(listener) {
    fun getApiService(): ApiService =
        provideRetrofit(provideOkHttpClient(), Feature.UserService).create(ApiService::class.java)
}