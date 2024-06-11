package com.c241ps294.sikarir.data.preference.user

data class User (
    val username: String,
    val name: String,
    val userId: String,
    val token: String,
    val isTakenQuiz: Boolean,
    val isLogin: Boolean = false
)