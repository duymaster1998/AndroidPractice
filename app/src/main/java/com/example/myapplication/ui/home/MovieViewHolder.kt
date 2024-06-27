package com.example.myapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.model.MovieModel
import com.example.myapplication.databinding.ItemMovieBinding

class MovieViewHolder(
    private val itemMovieBinding: ItemMovieBinding,
) : RecyclerView.ViewHolder(itemMovieBinding.root) {
    fun bind(
        movieModel: MovieModel,
        listener: (MovieModel) -> Unit
    ) {
        itemMovieBinding.apply {
            tvName.text = movieModel.title
            Glide.with(ivMovie.context)
                .load("${BuildConfig.BASE_URL_IMAGE}${movieModel.backdropPath}")
                .into(ivMovie)
            ivMovie.setOnClickListener {
                listener.invoke(movieModel)
            }
        }
    }

    companion object {
        fun from(viewGroup: ViewGroup): MovieViewHolder {
            val inflater = LayoutInflater.from(viewGroup.context)
            val itemMovieBinding = ItemMovieBinding.inflate(
                inflater,
                viewGroup,
                false
            )
            return MovieViewHolder(itemMovieBinding)
        }
    }
}