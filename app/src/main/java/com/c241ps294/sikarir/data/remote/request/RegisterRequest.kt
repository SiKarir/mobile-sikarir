package com.c241ps294.sikarir.data.remote.request

data class RegisterRequest(
    val username: String,
    val name: String,
    val email: String,
    val password: String
)