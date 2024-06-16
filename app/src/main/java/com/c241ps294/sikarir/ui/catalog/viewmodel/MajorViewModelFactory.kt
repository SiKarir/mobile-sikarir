package com.c241ps294.sikarir.ui.catalog.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.c241ps294.sikarir.data.remote.retrofit.ApiConfig
import com.c241ps294.sikarir.data.repository.MajorRepository
import com.c241ps294.sikarir.di.MajorInjection

class MajorViewModelFactory(private val majorRepository: MajorRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MajorViewModel::class.java) -> {
                MajorViewModel(majorRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: MajorViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): MajorViewModelFactory {
            if (INSTANCE == null) {
                synchronized(MajorViewModelFactory::class.java) {
                    INSTANCE = MajorViewModelFactory(MajorInjection.provideRepository(context, ApiConfig.getApiService()))
                }
            }
            return INSTANCE as MajorViewModelFactory
        }
    }
}