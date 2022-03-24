package com.neowise.logintest.api

import com.neowise.logintest.api.model.LoginRequest
import com.neowise.logintest.api.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("aloqa.php")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}