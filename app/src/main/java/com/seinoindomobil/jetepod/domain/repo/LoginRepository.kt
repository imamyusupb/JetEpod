package com.seinoindomobil.jetepod.domain.repo

import com.seinoindomobil.jetepod.data.remote.dto.login.LoginRequest
import com.seinoindomobil.jetepod.domain.model.Login
import com.seinoindomobil.jetepod.utils.Resource

interface LoginRepository {

    suspend fun postLogin(
        loginRequest: LoginRequest
    ): Resource<Login>
}