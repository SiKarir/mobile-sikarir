package com.c241ps294.sikarir.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.c241ps294.sikarir.data.remote.retrofit.ApiConfig
import com.c241ps294.sikarir.data.repository.QuizRepository
import com.c241ps294.sikarir.di.QuizInjection

class AccountViewModelFactory(private val repository: QuizRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AccountViewModel::class.java) -> {
                AccountViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AccountViewModelFactory? = null
        @JvmStatic
        fun getInstance(): AccountViewModelFactory {
            if (INSTANCE == null) {
                synchronized(AccountViewModelFactory::class.java) {
                    val apiService = ApiConfig.getApiService()
                    INSTANCE = AccountViewModelFactory(QuizInjection.provideRepository(apiService))
                }
            }
            return INSTANCE as AccountViewModelFactory
        }
    }
}