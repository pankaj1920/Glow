package com.developer.u_glow.webservices

import com.base.app.listener.IRepositoryListener
import com.base.app.model.State
import com.base.app.webservice.NetworkBoundRepository
import com.developer.u_glow.model.request.*
import com.developer.u_glow.model.response.*
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ModelRepository(private val iRepositoryListener: IRepositoryListener?) {

    private val apiService: RetrofitClient =
        RetrofitClient(iRepositoryListener)

    fun updateSampleInfo(sampleRequest: SampleRequest): Flow<State<SampleResponse>> {
        return object : NetworkBoundRepository<SampleResponse>(iRepositoryListener) {
            override suspend fun fetchData(): Response<SampleResponse> =
                apiService.getApiService().updateSampleApi(sampleRequest)
        }.asFlow()
    }

    fun performLogin(request: LoginRequest): Flow<State<LoginResponse>> {
        return object : NetworkBoundRepository<LoginResponse>(iRepositoryListener) {
            override suspend fun fetchData(): Response<LoginResponse> =
                apiService.getApiService().performLogin(request)
        }.asFlow()
    }

    fun performGoogleLogin(request: GoogleRequest): Flow<State<LoginResponse>> {
        return object : NetworkBoundRepository<LoginResponse>(iRepositoryListener) {
            override suspend fun fetchData(): Response<LoginResponse> =
                apiService.getApiService().performGoogleLogin(request)
        }.asFlow()
    }

    fun performFbLogin(request: FbRequest): Flow<State<LoginResponse>> {
        return object : NetworkBoundRepository<LoginResponse>(iRepositoryListener) {
            override suspend fun fetchData(): Response<LoginResponse> =
                apiService.getApiService().performFbLogin(request)
        }.asFlow()
    }

    fun getAllCategory(): Flow<State<CategoryResponse>> {
        return object : NetworkBoundRepository<CategoryResponse>(iRepositoryListener) {
            override suspend fun fetchData(): Response<CategoryResponse> =
                apiService.getApiService().getAllCategory()

        }.asFlow()
    }

    fun getSubCategory(id:Int): Flow<State<CategoryResponse>> {

        return object : NetworkBoundRepository<CategoryResponse>(iRepositoryListener) {
            override suspend fun fetchData(): Response<CategoryResponse> =
                apiService.getApiService().getSubCategory(id)

        }.asFlow()
    }

    fun getService(id:Int): Flow<State<SelectServiceRespose>> {

        return object : NetworkBoundRepository<SelectServiceRespose>(iRepositoryListener) {
            override suspend fun fetchData(): Response<SelectServiceRespose> =
                apiService.getApiService().getService(id)

        }.asFlow()
    }
    fun getOpenBooking(): Flow<State<OpenBookingResponse>> {

        return object : NetworkBoundRepository<OpenBookingResponse>(iRepositoryListener) {
            override suspend fun fetchData(): Response<OpenBookingResponse> =
                apiService.getApiService().getOpenBooking()
        }.asFlow()
    }

    fun postGlow(request: PostGlowRequest): Flow<State<PostGlowResponse>> {
        return object : NetworkBoundRepository<PostGlowResponse>(iRepositoryListener) {
            override suspend fun fetchData(): Response<PostGlowResponse> =
                apiService.getApiService().postGlow(request)
        }.asFlow()
    }
}