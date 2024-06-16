package com.c241ps294.sikarir.di

import android.content.Context
import com.c241ps294.sikarir.data.database.CareerDatabase
import com.c241ps294.sikarir.data.remote.retrofit.ApiService
import com.c241ps294.sikarir.data.repository.CareerRepository

object CareerInjection {
    fun provideRepository(context: Context, apiService: ApiService): CareerRepository {
        val database = CareerDatabase.getDatabase(context)
        return CareerRepository.getInstance(database, apiService)
    }
}