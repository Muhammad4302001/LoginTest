package com.neowise.logintest.api.model

class LoginRequest(
    val method: String,
    val password: String,
    val phone: Int
)
