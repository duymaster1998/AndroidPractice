package com.example.myapplication.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.data.model.MovieDiffUtil
import com.example.myapplication.data.model.MovieModel

class MovieAdapter(
    private val listener: (MovieModel) -> Unit
) : ListAdapter<MovieModel, MovieViewHolder>(MovieDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it, listener)
        }
    }
}