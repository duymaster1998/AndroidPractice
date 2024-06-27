package com.example.myapplication.ui.home

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.myapplication.data.model.MovieDiffUtil
import com.example.myapplication.data.model.MovieModel

class MoviePagingAdapter(
    private val listener: (MovieModel)-> Unit
) : PagingDataAdapter<MovieModel, MovieViewHolder>(MovieDiffUtil) {
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }
}