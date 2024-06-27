package com.example.myapplication.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,

) : Parcelable

data class MovieResult(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieModel>,
)

object MovieDiffUtil : DiffUtil.ItemCallback<MovieModel>() {
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean = oldItem == newItem
}
