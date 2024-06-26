package com.c241ps294.sikarir.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.c241ps294.sikarir.data.database.CareerDatabase
import com.c241ps294.sikarir.data.mediator.CareerRemoteMediator
import com.c241ps294.sikarir.data.remote.response.ListCareerItem
import com.c241ps294.sikarir.data.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CareerRepository(private val careerDatabase: CareerDatabase, private val apiService: ApiService) {
    fun getCareers(): LiveData<PagingData<ListCareerItem>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = CareerRemoteMediator(careerDatabase, apiService),
            pagingSourceFactory = {
                careerDatabase.careerDao().getAllCareers()
            }
        ).liveData
    }

    suspend fun searchCareers(query: String): LiveData<List<ListCareerItem>> {
        val searchResults = MutableLiveData<List<ListCareerItem>>()
        withContext(Dispatchers.IO) {
            val response = apiService.searchCareers(query)
            searchResults.postValue(response.listCareer)
        }
        return searchResults
    }

    companion object {
        @Volatile
        private var instance: CareerRepository? = null
        fun getInstance(careerDatabase: CareerDatabase, apiService: ApiService) : CareerRepository = instance ?: synchronized(this) {
            instance ?: CareerRepository(careerDatabase, apiService) }.also { instance = it }
    }
}