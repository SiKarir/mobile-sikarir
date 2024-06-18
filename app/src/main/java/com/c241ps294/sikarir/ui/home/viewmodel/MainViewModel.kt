package com.c241ps294.sikarir.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c241ps294.sikarir.data.remote.response.ListMajorItem
import com.c241ps294.sikarir.data.remote.response.QuizItem
import com.c241ps294.sikarir.data.repository.MajorRepository
import com.c241ps294.sikarir.data.repository.QuizRepository
import kotlinx.coroutines.launch

class MainViewModel(private val majorRepository: MajorRepository, private val quizRepository: QuizRepository) : ViewModel() {

    private val _majors = MutableLiveData<List<ListMajorItem>>()
    val majors: LiveData<List<ListMajorItem>> = _majors

    private val _quizzes = MutableLiveData<List<QuizItem>>()
    val quizzes: LiveData<List<QuizItem>> = _quizzes

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetch5RandomMajors()
    }

    private fun fetch5RandomMajors() {
        viewModelScope.launch {
            _isLoading.value = true
            val data = majorRepository.get5RandomMajors()
            _majors.postValue(data.value)
            _isLoading.value = false
        }
    }

    fun getQuizHistory(token: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val data = quizRepository.getQuizHistory(token)
            _quizzes.postValue(data.value)
            _isLoading.value = false
        }
    }
}
