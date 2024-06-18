package com.c241ps294.sikarir.ui.catalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.c241ps294.sikarir.data.remote.response.ListCareerItem
import com.c241ps294.sikarir.data.remote.response.QuizItem
import com.c241ps294.sikarir.data.repository.CareerRepository
import com.c241ps294.sikarir.data.repository.QuizRepository
import kotlinx.coroutines.launch

class CareerViewModel(private val careerRepository: CareerRepository, private val quizRepository: QuizRepository) : ViewModel() {

    val careers: LiveData<PagingData<ListCareerItem>> =
        careerRepository.getCareers().cachedIn(viewModelScope)

    private val _searchResults = MutableLiveData<List<ListCareerItem>>()
    val searchResults: LiveData<List<ListCareerItem>> get() = _searchResults

    fun searchCareers(query: String) {
        viewModelScope.launch {
            _searchResults.value = careerRepository.searchCareers(query).value
        }
    }

    private val _quizzes = MutableLiveData<List<QuizItem>>()
    val quizzes: LiveData<List<QuizItem>> = _quizzes

    fun getQuizHistory(token: String) {
        viewModelScope.launch {
            val data = quizRepository.getQuizHistory(token)
            _quizzes.postValue(data.value)
        }
    }
}