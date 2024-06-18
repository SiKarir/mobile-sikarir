package com.c241ps294.sikarir.ui.quiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c241ps294.sikarir.data.local.storage.AnswerStorage
import com.c241ps294.sikarir.data.remote.response.QuizResponse
import com.c241ps294.sikarir.data.repository.QuizRepository
import kotlinx.coroutines.launch

class QuizViewModel(private val quizRepository: QuizRepository) : ViewModel() {

    private val _quizResponse = MutableLiveData<QuizResponse>()
    val quizResponse: LiveData<QuizResponse> = _quizResponse

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun postQuiz(token: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = quizRepository.postQuiz(token = token, answerHashMap = AnswerStorage.getAnswers())
                _quizResponse.value = response
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = e.message
            }
        }
    }
}