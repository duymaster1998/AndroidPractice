package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.myapplication.data.local.AppDatabase
import com.example.myapplication.data.local.entity.MovieEntity
import com.example.myapplication.data.mappers.toMovieModel
import com.example.myapplication.data.model.MovieModel
import com.example.myapplication.data.network.ApiService
import com.example.myapplication.data.paging.MoviePagingSource
import com.example.myapplication.data.paging.MovieRemoteMediator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService,
    pager: Pager<Int, MovieEntity>
): ViewModel() {

    private val _movies = MutableLiveData<List<MovieModel>>()
    val movies: LiveData<List<MovieModel>>
        get() = _movies
    fun getMovies(){
        viewModelScope.launch {
            val movieList = apiService.getMovies(page = 1)
            _movies.postValue(movieList.movies)
        }
    }
    val moviesData: Flow<PagingData<MovieModel>> = Pager(
        PagingConfig(10,5)
    ){
        MoviePagingSource(apiService)
    }.flow
        .cachedIn(viewModelScope)

    val moviesRemoteMediator = pager
        .flow
        .map { pagingData ->
            pagingData.map {
                it.toMovieModel()
            }
        }
        .cachedIn(viewModelScope)
}