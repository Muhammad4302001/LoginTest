package com.neowise.logintest.api.model.userInfo

import com.google.gson.annotations.SerializedName


data class UserInfo(

    @SerializedName("user_token") var userToken: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("balanse") var balanse: Int? = null,
    @SerializedName("bonus") var bonus: String? = null,
    @SerializedName("banned") var banned: String? = null,
    @SerializedName("first_time") var firstTime: String? = null,
    @SerializedName("change_pass") var changePass: String? = null

)
