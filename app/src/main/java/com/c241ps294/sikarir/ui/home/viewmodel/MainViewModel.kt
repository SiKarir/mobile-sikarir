package com.c241ps294.sikarir.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c241ps294.sikarir.data.remote.response.ListMajorItem
import com.c241ps294.sikarir.data.repository.MajorRepository
import kotlinx.coroutines.launch

class MainViewModel(private val majorRepository: MajorRepository) : ViewModel() {

    private val _majors = MutableLiveData<List<ListMajorItem>>()
    val majors: LiveData<List<ListMajorItem>> get() = _majors

    init {
        fetch5RandomMajors()
    }

    private fun fetch5RandomMajors() {
        viewModelScope.launch {
            val data = majorRepository.get5RandomMajors()
            _majors.postValue(data.value)
        }
    }
}
