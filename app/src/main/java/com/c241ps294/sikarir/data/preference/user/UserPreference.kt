package com.c241ps294.sikarir.data.preference.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveSession(user: User) {
        dataStore.edit { preferences ->
            preferences[USERNAME] = user.username
            preferences[TOKEN] = user.token
            preferences[NAME] = user.name
            preferences[PASSWORD] = user.password
            preferences[EMAIL] = user.email
            preferences[USER_ID] = user.userId
            preferences[IS_TAKEN_QUIZ] = user.isTakenQuiz
            preferences[PHOTO_URL] = user.photoUrl
            preferences[IS_LOGIN_KEY] = true
        }
    }

    fun getSession(): Flow<User> {
        return dataStore.data.map { preferences ->
            User(
                username = preferences[USERNAME] ?: "Not Set",
                token = preferences[TOKEN] ?: "Not Set",
                email = preferences[EMAIL] ?: "Not Set",
                name = preferences[NAME] ?: "Not Set",
                userId = preferences[USER_ID] ?: "Not Set",
                isTakenQuiz = preferences[IS_TAKEN_QUIZ] ?: false,
                isLogin = preferences[IS_LOGIN_KEY] ?: false,
                photoUrl = preferences[PHOTO_URL] ?: "Not Set",
                password = preferences[PASSWORD] ?: "Not Set"
            )
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val USERNAME = stringPreferencesKey("username")
        private val TOKEN = stringPreferencesKey("token")
        private val EMAIL = stringPreferencesKey("email")
        private val NAME = stringPreferencesKey("name")
        private val PASSWORD = stringPreferencesKey("password")
        private val PHOTO_URL = stringPreferencesKey("photo_url")
        private val USER_ID = stringPreferencesKey("user_id")
        private val IS_TAKEN_QUIZ = booleanPreferencesKey("isTakenQuiz")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}