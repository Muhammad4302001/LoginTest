package com.neowise.logintest.api.model.userInfo

data class UserInfoRequest(
    val method: String,
    val user_token: String,
)