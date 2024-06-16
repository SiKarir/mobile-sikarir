package com.c241ps294.sikarir.data.remote.retrofit

import retrofit2.http.Query
import com.c241ps294.sikarir.data.remote.request.EditAccountRequest
import com.c241ps294.sikarir.data.remote.request.LoginRequest
import com.c241ps294.sikarir.data.remote.request.RegisterRequest
import com.c241ps294.sikarir.data.remote.response.CareerResponse
import com.c241ps294.sikarir.data.remote.response.EditAccountResponse
import com.c241ps294.sikarir.data.remote.response.LoginResponse
import com.c241ps294.sikarir.data.remote.response.MajorResponse
import com.c241ps294.sikarir.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.GET
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

    @GET("catalog/careers")
    suspend fun getCareers(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 5
    ): CareerResponse

    @GET("catalog/majors")
    suspend fun getMajors(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 5
    ): MajorResponse

    @GET("search/majors")
    suspend fun searchMajors(@Query("q") query: String): MajorResponse

    @GET("search/careers")
    suspend fun searchCareers(@Query("q") query: String): CareerResponse

    @GET("catalog/majors/random")
    suspend fun get5RandomMajors(): MajorResponse
}