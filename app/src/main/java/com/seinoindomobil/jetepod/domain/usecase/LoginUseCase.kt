package com.seinoindomobil.jetepod.domain.usecase

import com.seinoindomobil.jetepod.data.remote.dto.login.LoginRequest
import com.seinoindomobil.jetepod.domain.model.Login
import com.seinoindomobil.jetepod.domain.repo.LoginRepository
import com.seinoindomobil.jetepod.utils.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    suspend fun execute(loginRequest: LoginRequest): Resource<Login> {
        return repository.postLogin(loginRequest)
    }
}