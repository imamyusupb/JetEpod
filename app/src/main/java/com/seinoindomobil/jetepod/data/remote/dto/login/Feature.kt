package com.seinoindomobil.jetepod.data.remote.dto.login

data class Feature(
    val category: String,
    val code: String,
    val has_child: Boolean,
    val icon: String,
    val id: String,
    val name: String,
    val `package`: String,
    val parrent: String
)