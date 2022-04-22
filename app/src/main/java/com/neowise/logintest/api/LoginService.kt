package com.neowise.logintest.api

import com.neowise.logintest.api.model.LoginRequest
import com.neowise.logintest.api.model.LoginResponse
import com.neowise.logintest.api.model.Metan
import com.neowise.logintest.api.model.userInfo.UserInfo
import com.neowise.logintest.api.model.userInfo.UserInfoRequest
import com.neowise.logintest.request.MetanRequest
import retrofit2.Call
import retrofit2.http.*

interface LoginService {




    @POST("aloqa.php")
    fun login(@Body request: LoginRequest): Call<LoginResponse>


    @POST("aloqa.php")
    fun fetchPosts(@Body requestInfo: UserInfoRequest): Call<UserInfo>

    @POST("aloqa.php")
    fun getMetan(@Body metanRequest: MetanRequest): Call<Metan>

}