package com.neowise.logintest.api

import android.content.Context
import com.neowise.logintest.Session.LoginPref
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor


fun loggingInterceptor(): HttpLoggingInterceptor {

    return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
}



class AuthInterceptor(context: Context) : Interceptor {
    private val sessionManager = LoginPref(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
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