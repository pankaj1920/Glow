package com.developer.u_glow.webservices

import com.developer.u_glow.model.request.*
import com.developer.u_glow.model.response.*
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    @POST("v1/api/sampleApi")
    suspend fun updateSampleApi(@Body request: SampleRequest): Response<SampleResponse>

    @POST("api/v1/user/login")
    suspend fun performLogin(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/v1/user/auth/google")
    suspend fun performGoogleLogin(@Body request: GoogleRequest): Response<LoginResponse>

    @POST("api/v1/user/auth/facebook")
    suspend fun performFbLogin(@Body request: FbRequest): Response<LoginResponse>

    @GET("api/v1/category/all")
    suspend fun getAllCategory(
    ): Response<CategoryResponse>

    @GET("api/v1/category/subcategory/{id}")
    suspend fun getSubCategory(
        @Path("id") id:Int
    ): Response<CategoryResponse>

    @GET("api/v1/category/services/{id}")
    suspend fun getService(
        @Path("id") id:Int
    ): Response<SelectServiceRespose>

    @POST("api/v1/booking/create")
    suspend fun postGlow(
        @Body request: PostGlowRequest
    ): Response<PostGlowResponse>

    @GET("api/v1/booking/open?pageNo=1&pageLimit=5&search=brow")
    suspend fun getOpenBooking(
    ): Response<OpenBookingResponse>

}