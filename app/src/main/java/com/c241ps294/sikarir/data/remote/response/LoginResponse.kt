package com.c241ps294.sikarir.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("loginResult")
    val loginResult: LoginResult

)

data class LoginResult(

    @field:SerializedName("userId")
    val userId: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("isTakenQuiz")
    val isTakenQuiz: Boolean,

    @field:SerializedName("photoUrl")
    val photoUrl: String,

    @field:SerializedName("token")
    val token: String

)