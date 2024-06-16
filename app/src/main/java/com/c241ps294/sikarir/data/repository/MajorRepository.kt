package com.c241ps294.sikarir.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.c241ps294.sikarir.data.database.MajorDatabase
import com.c241ps294.sikarir.data.paging.MajorPagingSource
import com.c241ps294.sikarir.data.remote.response.ListMajorItem
import com.c241ps294.sikarir.data.remote.retrofit.ApiService

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

    suspend fun searchMajors(query: String): List<ListMajorItem> {
        val response = apiService.searchMajors(query)
        return response.listMajor?.filterNotNull() ?: emptyList()
    }

    companion object {
        private const val TAG = "MajorRepository"

        @Volatile
        private var instance: MajorRepository? = null
        fun getInstance(majorDatabase: MajorDatabase, apiService: ApiService) : MajorRepository = instance ?: synchronized(this) {
            instance ?: MajorRepository(majorDatabase, apiService) }.also { instance = it }
    }
}

