package com.c241ps294.sikarir.di

import android.content.Context
import com.c241ps294.sikarir.data.preference.user.UserPreference
import com.c241ps294.sikarir.data.preference.user.dataStore
import com.c241ps294.sikarir.data.repository.UserRepository

object UserInjection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}