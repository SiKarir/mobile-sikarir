package com.c241ps294.sikarir.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.c241ps294.sikarir.data.database.MajorDatabase
import com.c241ps294.sikarir.data.mediator.MajorRemoteMediator
import com.c241ps294.sikarir.data.remote.response.ListMajorItem
import com.c241ps294.sikarir.data.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MajorRepository(private val majorDatabase: MajorDatabase, private val apiService: ApiService) {
    fun getMajors(): LiveData<PagingData<ListMajorItem>>{
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = MajorRemoteMediator(majorDatabase, apiService),
            pagingSourceFactory = {
                majorDatabase.majorDao().getAllMajors()
            }
        ).liveData
    }

    suspend fun searchMajors(query: String): LiveData<List<ListMajorItem>> {
        val searchResults = MutableLiveData<List<ListMajorItem>>()
        withContext(Dispatchers.IO) {
            val response = apiService.searchMajors(query)
            searchResults.postValue(response.listMajor)
        }
        return searchResults
    }

    suspend fun get5RandomMajors() : LiveData<List<ListMajorItem>> {
        val majors = MutableLiveData<List<ListMajorItem>>()
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.get5RandomMajors()
                majors.postValue(response.listMajor)
            } catch (e: Exception) {
                majors.postValue(emptyList())
            }
        }
        return majors
    }

    companion object {
        @Volatile
        private var instance: MajorRepository? = null
        fun getInstance(majorDatabase: MajorDatabase, apiService: ApiService) : MajorRepository = instance ?: synchronized(this) {
            instance ?: MajorRepository(majorDatabase, apiService) }.also { instance = it }
    }
}

