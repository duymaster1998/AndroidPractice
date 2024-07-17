package com.example.myapplication.data.mappers

import com.example.myapplication.data.local.entity.MovieEntity
import com.example.myapplication.data.model.MovieModel

fun MovieModel.toMovieEntity(): MovieEntity = MovieEntity(
    id = id,
    originalTitle = originalTitle,
    title = title,
    overview = overview,
    backdropPath = backdropPath,
    posterPath = posterPath,
    releaseDate = releaseDate
)

fun MovieEntity.toMovieModel(): MovieModel = MovieModel(
    id,
    originalTitle,
    title,
    overview,
    backdropPath,
    posterPath,
    releaseDate
)