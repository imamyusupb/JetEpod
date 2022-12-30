package com.seinoindomobil.jetepod.data.remote

import com.seinoindomobil.jetepod.data.remote.dto.login.LoginDTO
import com.seinoindomobil.jetepod.data.remote.dto.login.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiClient {

    @POST("auth/v1/login")
    suspend fun login(@Body loginRequest: LoginRequest) : LoginDTO
}