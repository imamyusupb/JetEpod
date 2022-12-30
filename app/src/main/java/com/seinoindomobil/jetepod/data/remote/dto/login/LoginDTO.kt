package com.seinoindomobil.jetepod.data.remote.dto.login


data class LoginDTO(
    val result: Result,
    val status: String,
    val status_code: Int,
    val timestamp: String,
)

data class LoginRequest(
    val username:String,
    val password:String
)
