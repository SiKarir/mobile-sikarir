package com.c241ps294.sikarir.di

import com.c241ps294.sikarir.data.remote.retrofit.ApiService
import com.c241ps294.sikarir.data.repository.QuizRepository

object QuizInjection {
    fun provideRepository(apiService: ApiService): QuizRepository {
        return QuizRepository.getInstance(apiService)
    }
}