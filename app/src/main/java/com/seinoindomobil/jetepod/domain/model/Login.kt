package com.seinoindomobil.jetepod.domain.model

data class Login(
    val id: String,
    val active: String,
    val activated: String,
    val lock: String,
    val division: String,
    val department: String,
    val section: String,
    val unit: String,
    val area: String,
    val email: String,
    val phone: String,
    val gender: String,
    val name: String,
    val company: String,
    val token: String,
    val refresh_token: String
)
