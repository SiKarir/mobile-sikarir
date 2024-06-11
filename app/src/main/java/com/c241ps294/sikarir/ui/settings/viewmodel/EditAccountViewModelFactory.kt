package com.c241ps294.sikarir.ui.settings.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.c241ps294.sikarir.data.repository.UserRepository
import com.c241ps294.sikarir.di.UserInjection

class EditAccountViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(EditAccountViewModel::class.java) -> {
                EditAccountViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: EditAccountViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): EditAccountViewModelFactory {
            if (INSTANCE == null) {
                synchronized(EditAccountViewModelFactory::class.java) {
                    INSTANCE = EditAccountViewModelFactory(UserInjection.provideRepository(context))
                }
            }
            return INSTANCE as EditAccountViewModelFactory
        }
    }
}