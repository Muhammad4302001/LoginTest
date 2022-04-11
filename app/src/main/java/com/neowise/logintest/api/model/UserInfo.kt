package com.neowise.logintest.api.model

data class UserInfo(
    val balanse: Int,
    val banned: String,
    val bonus: String,
    val change_pass: String,
    val first_time: String,
    val user_token: String,
    val username: String
)