package com.neowise.logintest.api

import android.util.Base64
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private val AUTH =
        "Basic " + Base64.encodeToString("904302001:4302001".toByteArray(), Base64.NO_WRAP)
    private const val BASE_URL = "https://metan.mbos.uz/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor(AUTH))
        .addInterceptor(loggingInterceptor())
        .build()

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val loginService: LoginService by lazy {
        buildRetrofit().create(LoginService::class.java)
    }




}
