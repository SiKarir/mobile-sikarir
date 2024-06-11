package com.c241ps294.sikarir.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.c241ps294.sikarir.data.preference.user.User
import com.c241ps294.sikarir.data.preference.user.UserPreference
import com.c241ps294.sikarir.data.remote.response.LoginResponse
import com.c241ps294.sikarir.data.remote.response.RegisterResponse
import com.c241ps294.sikarir.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository private constructor(
    private val userPreference: UserPreference
) {

    suspend fun saveSession(user: User) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<User> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(userPreference: UserPreference): UserRepository = instance ?: synchronized(this) {
            instance ?: UserRepository(userPreference) }.also { instance = it }
    }
}