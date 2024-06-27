package com.example.myapplication.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.data.model.MovieModel
import com.example.myapplication.data.network.ApiService

class MoviePagingSource(
    val apiService: ApiService
) : PagingSource<Int, MovieModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        try {
            val nextPage = params.key ?: 1
            val response = apiService.getMovies(page = nextPage)

            return LoadResult.Page(
                data = response.movies,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = response.page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}