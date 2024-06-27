package com.example.myapplication.data.network

import com.example.myapplication.data.model.MovieResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
    ): MovieResult
}