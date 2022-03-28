package com.neowise.logintest.api.model.userInfo

import com.google.gson.annotations.SerializedName

data class UserInfoModel(


    @SerializedName("user_info") var userInfo: UserInfo? = UserInfo()

)