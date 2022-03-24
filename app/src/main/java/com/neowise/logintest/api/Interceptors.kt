package com.neowise.logintest.api

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor


fun loggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
}

fun authInterceptor(auth: String): (Interceptor.Chain) -> Response {
    return { chain: Interceptor.Chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader("Authorization", auth)
            .method(original.method, original.body)
        val request = requestBuilder.build()
        chain.proceed(request)
    }
}