package com.c241ps294.sikarir.ui.catalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.c241ps294.sikarir.data.remote.response.ListCareerItem
import com.c241ps294.sikarir.data.remote.response.ListMajorItem
import com.c241ps294.sikarir.data.repository.MajorRepository
import kotlinx.coroutines.launch

class MajorViewModel(private val majorRepository: MajorRepository) : ViewModel() {

    val majors: LiveData<PagingData<ListMajorItem>> =
        majorRepository.getMajors().cachedIn(viewModelScope)

    private val _searchResults = MutableLiveData<List<ListMajorItem>>()
    val searchResults: LiveData<List<ListMajorItem>> get() = _searchResults

    fun searchMajors(query: String) {
        viewModelScope.launch {
            _searchResults.value = majorRepository.searchMajors(query).value
        }
    }
}