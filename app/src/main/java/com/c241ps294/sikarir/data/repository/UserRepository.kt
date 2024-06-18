package com.c241ps294.sikarir.data.repository

import com.c241ps294.sikarir.data.preference.user.User
import com.c241ps294.sikarir.data.preference.user.UserPreference
import kotlinx.coroutines.flow.Flow

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