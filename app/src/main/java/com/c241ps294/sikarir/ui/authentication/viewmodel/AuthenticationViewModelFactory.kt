package com.c241ps294.sikarir.ui.authentication.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.c241ps294.sikarir.data.repository.UserRepository
import com.c241ps294.sikarir.di.UserInjection

class AuthenticationViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthenticationViewModel::class.java) -> {
                AuthenticationViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AuthenticationViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): AuthenticationViewModelFactory {
            if (INSTANCE == null) {
                synchronized(AuthenticationViewModelFactory::class.java) {
                    INSTANCE = AuthenticationViewModelFactory(UserInjection.provideRepository(context))
                }
            }
            return INSTANCE as AuthenticationViewModelFactory
        }
    }
}