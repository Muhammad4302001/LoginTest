package com.neowise.logintest.api

import com.neowise.logintest.api.model.LoginRequest
import com.neowise.logintest.api.model.LoginResponse
import com.neowise.logintest.api.model.userInfo.UserInfoModel
import com.neowise.logintest.api.model.userInfo.UserInfoRequest
import retrofit2.Call
import retrofit2.http.*

interface LoginService {




    @POST("aloqa.php")
    fun login(@Body request: LoginRequest): Call<LoginResponse>


    @GET("aloqa.php")
    fun fetchPosts(requestInfo: UserInfoRequest): Call<UserInfoModel>

}