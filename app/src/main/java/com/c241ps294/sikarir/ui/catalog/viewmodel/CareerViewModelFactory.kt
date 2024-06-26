package com.c241ps294.sikarir.ui.catalog.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.c241ps294.sikarir.data.remote.retrofit.ApiConfig
import com.c241ps294.sikarir.data.repository.CareerRepository
import com.c241ps294.sikarir.data.repository.QuizRepository
import com.c241ps294.sikarir.di.CareerInjection
import com.c241ps294.sikarir.di.QuizInjection

class CareerViewModelFactory(private val careerRepository: CareerRepository, private val quizRepository: QuizRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CareerViewModel::class.java) -> {
                CareerViewModel(careerRepository, quizRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: CareerViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): CareerViewModelFactory {
            if (INSTANCE == null) {
                synchronized(CareerViewModelFactory::class.java) {
                    INSTANCE = CareerViewModelFactory(CareerInjection.provideRepository(context, ApiConfig.getApiService()), QuizInjection.provideRepository(ApiConfig.getApiService()))
                }
            }
            return INSTANCE as CareerViewModelFactory
        }
    }
}