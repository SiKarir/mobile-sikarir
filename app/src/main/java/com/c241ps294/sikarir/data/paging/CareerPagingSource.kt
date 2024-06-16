package com.c241ps294.sikarir.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.c241ps294.sikarir.data.remote.response.ListCareerItem
import com.c241ps294.sikarir.data.remote.retrofit.ApiService
import retrofit2.HttpException
import java.io.IOException

class CareerPagingSource(private val apiService: ApiService) : PagingSource<Int, ListCareerItem>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, ListCareerItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListCareerItem> {
        val position = params.key ?: INITIAL_PAGE_INDEX
        return try {
            val response = apiService.getCareers(page = position, size = params.loadSize)
            val careers = response.listCareer ?: emptyList()
            LoadResult.Page(
                data = careers.filterNotNull(), // Filter out null items if any
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (careers.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            // Handle network errors
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            // Handle HTTP errors
            LoadResult.Error(exception)
        }
    }
}