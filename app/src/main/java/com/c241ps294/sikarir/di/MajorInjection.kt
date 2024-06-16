package com.c241ps294.sikarir.di

import android.content.Context
import com.c241ps294.sikarir.data.database.CareerDatabase
import com.c241ps294.sikarir.data.database.MajorDatabase
import com.c241ps294.sikarir.data.remote.retrofit.ApiService
import com.c241ps294.sikarir.data.repository.CareerRepository
import com.c241ps294.sikarir.data.repository.MajorRepository

object MajorInjection {
    fun provideRepository(context: Context, apiService: ApiService): MajorRepository {
        val database = MajorDatabase.getDatabase(context)
        return MajorRepository.getInstance(database, apiService)
    }
}