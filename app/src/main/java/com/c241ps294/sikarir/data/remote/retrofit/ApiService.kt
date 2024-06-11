package com.c241ps294.sikarir.data.remote.retrofit

import com.c241ps294.sikarir.data.remote.request.EditAccountRequest
import com.c241ps294.sikarir.data.remote.request.LoginRequest
import com.c241ps294.sikarir.data.remote.request.RegisterRequest
import com.c241ps294.sikarir.data.remote.response.EditAccountResponse
import com.c241ps294.sikarir.data.remote.response.LoginResponse
import com.c241ps294.sikarir.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.PUT

interface ApiService {

    @POST("login")
    fun login (
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    @POST("register")
    fun register (
        @Body registerRequest: RegisterRequest
    ): Call<RegisterResponse>

    @PUT("edit-account")
    fun editAccount(
        @Header("Authorization") token: String,
        @Body editAccountRequest: EditAccountRequest
    ): Call<EditAccountResponse>
}