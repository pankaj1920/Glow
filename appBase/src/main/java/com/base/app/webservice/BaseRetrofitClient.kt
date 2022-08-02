package com.base.app.webservice

import com.base.app.listener.IRepositoryListener
import com.base.app.BuildConfig
import com.base.app.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit


open class BaseRetrofitClient(var iRepositoryListener: IRepositoryListener?) {


    protected fun provideRetrofit(okHttpClient: OkHttpClient, feature: Feature): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BaseMapping.getUrl(feature))
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .client(okHttpClient)
            .build()
    }

    //Creates OkHttpClient
    protected fun provideOkHttpClient(): OkHttpClient {

        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS) //Connection time out set limit
        okHttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)  //Connection read time out set limit
        if (BuildConfig.DEBUG)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)

        if (iRepositoryListener?.getToken().isNullOrEmpty().not()) {
            Timber.d("""Token ${iRepositoryListener?.getToken()}""")
            okHttpClientBuilder.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header(Constants.Header.AUTHORIZATION, "Bearer "+iRepositoryListener?.getToken()!!)
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)

            }
        }else{
            okHttpClientBuilder.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)

            }
        }
        return okHttpClientBuilder.build()

    }

   // fun getRegisterService(): ApiService = provideRetrofit(provideOkHttpClient(), Feature.Login).create(ApiService::class.java)

   // fun getForgotPasswordService(): ApiService = provideRetrofit(provideOkHttpClient(), Feature.ForgotPassword).create(ApiService::class.java)



}