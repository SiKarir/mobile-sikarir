package com.c241ps294.sikarir.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.c241ps294.sikarir.data.database.MajorDatabase
import com.c241ps294.sikarir.data.paging.MajorPagingSource
import com.c241ps294.sikarir.data.remote.response.ListMajorItem
import com.c241ps294.sikarir.data.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MajorRepository(private val majorDatabase: MajorDatabase, private val apiService: ApiService) {
    fun getMajors(): LiveData<PagingData<ListMajorItem>>{
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                MajorPagingSource(apiService)
            }
        ).liveData
    }

    suspend fun searchMajors(query: String): LiveData<List<ListMajorItem>> {
        val searchResults = MutableLiveData<List<ListMajorItem>>()
        withContext(Dispatchers.IO) {
            val response = apiService.searchMajors(query)
            searchResults.postValue(response.listMajor?.filterNotNull() ?: emptyList())
        }
        return searchResults
    }

    companion object {
        private const val TAG = "MajorRepository"

        @Volatile
        private var instance: MajorRepository? = null
        fun getInstance(majorDatabase: MajorDatabase, apiService: ApiService) : MajorRepository = instance ?: synchronized(this) {
            instance ?: MajorRepository(majorDatabase, apiService) }.also { instance = it }
    }
}

