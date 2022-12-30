package com.seinoindomobil.jetepod.data.remote.repository

import com.seinoindomobil.jetepod.data.mapper.toLoginDomain
import com.seinoindomobil.jetepod.data.remote.ApiClient
import com.seinoindomobil.jetepod.data.remote.dto.login.LoginRequest
import com.seinoindomobil.jetepod.domain.model.Login
import com.seinoindomobil.jetepod.domain.repo.LoginRepository
import com.seinoindomobil.jetepod.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepositoryImpl @Inject constructor(
    private val api: ApiClient,
) : LoginRepository {
    override suspend fun postLogin(loginRequest: LoginRequest): Resource<Login> {
        return try {
            val result = api.login(loginRequest)
            Resource.Success(result.toLoginDomain())
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(message = e.message.toString(), data = null)
        }
    }
}