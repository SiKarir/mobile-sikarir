package com.c241ps294.sikarir.ui.settings.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c241ps294.sikarir.data.remote.response.QuizItem
import com.c241ps294.sikarir.data.repository.QuizRepository
import kotlinx.coroutines.launch

class AccountViewModel(private val quizRepository: QuizRepository) : ViewModel() {

    private val _quizzes = MutableLiveData<List<QuizItem>>()
    val quizzes: LiveData<List<QuizItem>> = _quizzes

    fun getQuizHistory(token: String) {
        viewModelScope.launch {
            val data = quizRepository.getQuizHistory(token)
            _quizzes.postValue(data.value)
        }
    }
}