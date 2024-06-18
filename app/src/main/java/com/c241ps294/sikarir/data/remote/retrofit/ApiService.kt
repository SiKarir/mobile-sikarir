package com.c241ps294.sikarir.data.remote.retrofit

import retrofit2.http.Query
import com.c241ps294.sikarir.data.remote.request.LoginRequest
import com.c241ps294.sikarir.data.remote.request.RegisterRequest
import com.c241ps294.sikarir.data.remote.response.CareerResponse
import com.c241ps294.sikarir.data.remote.response.EditAccountResponse
import com.c241ps294.sikarir.data.remote.response.LoginResponse
import com.c241ps294.sikarir.data.remote.response.MajorResponse
import com.c241ps294.sikarir.data.remote.response.QuizHistoryResponse
import com.c241ps294.sikarir.data.remote.response.QuizResponse
import com.c241ps294.sikarir.data.remote.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part

interface ApiService {

    @POST("login")
    fun login (
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    @POST("register")
    fun register (
        @Body registerRequest: RegisterRequest
    ): Call<RegisterResponse>

    @Multipart
    @PUT("edit-account")
    fun editAccount(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
        @Part("name") name: RequestBody,
        @Part("username") username: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
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

    @POST("quiz")
    suspend fun postQuiz (
        @Header("Authorization") token: String,
        @Body answerHashMap: HashMap<Int, String>
    ): QuizResponse

    @GET("quiz/history")
    suspend fun getQuizHistory (
        @Header("Authorization") token: String,
    ): QuizHistoryResponse
}