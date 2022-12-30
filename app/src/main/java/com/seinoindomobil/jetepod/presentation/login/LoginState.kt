package com.seinoindomobil.jetepod.presentation.login

import com.seinoindomobil.jetepod.domain.model.Login

data class LoginState (
    val isLoading:Boolean = false,
    val login:Login? = null,
    val error :String? = null
)