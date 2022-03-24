package com.neowise.logintest.api.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    // on Error
    val error: Boolean,
    val message: String,

    // on OK status
    val userId: String,

    @SerializedName("user_token")
    val userToken: String,

    val username: String,
    val balance: Double,
    val bonus: String,
    val banned: String,

    @SerializedName("first_time")
    val firstTime: String,
    @SerializedName("change_pass")
    val changePass: String
)