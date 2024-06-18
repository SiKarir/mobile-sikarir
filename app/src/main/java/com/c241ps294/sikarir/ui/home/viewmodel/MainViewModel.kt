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

    init {
        fetch5RandomMajors()
    }

    private fun fetch5RandomMajors() {
        viewModelScope.launch {
            val data = majorRepository.get5RandomMajors()
            _majors.postValue(data.value)
        }
    }

    fun getQuizHistory(token: String) {
        viewModelScope.launch {
            val data = quizRepository.getQuizHistory(token)
            _quizzes.postValue(data.value)
        }
    }
}
