package com.c241ps294.sikarir.ui.quiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.c241ps294.sikarir.data.remote.retrofit.ApiConfig
import com.c241ps294.sikarir.data.repository.QuizRepository
import com.c241ps294.sikarir.di.QuizInjection

class QuizViewModelFactory(private val repository: QuizRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(QuizViewModel::class.java) -> {
                QuizViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: QuizViewModelFactory? = null
        @JvmStatic
        fun getInstance(): QuizViewModelFactory {
            if (INSTANCE == null) {
                synchronized(QuizViewModelFactory::class.java) {
                    val apiService = ApiConfig.getApiService()
                    INSTANCE = QuizViewModelFactory(QuizInjection.provideRepository(apiService))
                }
            }
            return INSTANCE as QuizViewModelFactory
        }
    }
}