package com.c241ps294.sikarir.ui.home.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.c241ps294.sikarir.data.remote.retrofit.ApiConfig
import com.c241ps294.sikarir.data.repository.MajorRepository
import com.c241ps294.sikarir.data.repository.QuizRepository
import com.c241ps294.sikarir.di.MajorInjection
import com.c241ps294.sikarir.di.QuizInjection

class MainViewModelFactory(private val majorRepository: MajorRepository, private val quizRepository: QuizRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(majorRepository, quizRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: MainViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): MainViewModelFactory {
            if (INSTANCE == null) {
                synchronized(MainViewModelFactory::class.java) {
                    INSTANCE = MainViewModelFactory(MajorInjection.provideRepository(context, ApiConfig.getApiService()), QuizInjection.provideRepository(ApiConfig.getApiService()))
                }
            }
            return INSTANCE as MainViewModelFactory
        }
    }
}