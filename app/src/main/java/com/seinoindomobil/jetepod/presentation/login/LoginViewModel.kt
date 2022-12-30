package com.seinoindomobil.jetepod.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seinoindomobil.jetepod.data.local.datastore.DataStoreRepo
import com.seinoindomobil.jetepod.data.remote.dto.login.LoginRequest
import com.seinoindomobil.jetepod.domain.usecase.LoginUseCase
import com.seinoindomobil.jetepod.utils.Constants.USER_ID
import com.seinoindomobil.jetepod.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase,
    private val dataStoreRepository:DataStoreRepo
) : ViewModel() {

    var state by mutableStateOf(LoginState())
   val _hasLogin = getUserName()
    fun login(username: String, password: String) = viewModelScope.launch {
        state = state.copy(isLoading = true)
        val loginDeferred =
            async { useCase.execute(loginRequest = LoginRequest(username, password)) }
        when (val result = loginDeferred.await()) {
            is Resource.Success -> {
                state = state.copy(
                    login = result.data,
                    error = null,
                    isLoading = false
                )
              storeUserName(result.data!!.id)
            }
            is Resource.Error ->
                state = state.copy(
                isLoading = false,
                error = result.message,
                login = null
            )
            else -> Unit
        }

    }

    fun storeUserName(value:String) = runBlocking {
        dataStoreRepository.putString(USER_ID,value)
    }
    fun getUserName():String = runBlocking {
        dataStoreRepository.getString(USER_ID)!!
    }

    fun clearPreferences(key:String) = runBlocking {
        dataStoreRepository.clearPReferences(key)
    }



//            state = state.copy(isLoading = true)
//            val loginResult = if ()
//                async { useCase.execute(loginRequest = LoginRequest(username, password))}
//
//            when (val result = loginResult.await()) {
//                is Resource.Success -> {
//                    state = state.copy(
//                        login = result.data,
//                        isLoading = false,
//                        error = null
//                    )
//                }
//                is Resource.Error -> {
//                    state = state.copy(
//                        isLoading = false,
//                        error = result.message,
//                        login = null
//                    )
//                }
//                else -> {
//                    Unit
//                }
//            }
}
