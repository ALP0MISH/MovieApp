package com.example.autospareparts.presentation.mappers

import android.util.Log
import com.example.autospareparts.data.cashe.models.MovieDetailCache
import com.example.autospareparts.data.cloude.models.all_movies_remote.MovieResult
import com.example.autospareparts.data.cloude.models.detail_remote.DetailResult
import com.example.autospareparts.domain.models.MovieDetailDomain
import com.example.autospareparts.domain.models.MovieDomain
import com.example.autospareparts.presentation.models.DetailUi

const val POSTER_PATH_URL = "https://image.tmdb.org/t/p/w342"

fun MovieDetailDomain.toCache(): MovieDetailCache =
    this.run {
        MovieDetailCache(
            adult = adult,
            backdropPath = POSTER_PATH_URL + backdropPath,
            budget = budget,
            homepage = homepage,
            id = id,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = POSTER_PATH_URL + posterPath,
            releaseDate = releaseDate,
            runtime = runtime,
            voteAverage = voteAverage,
            voteCount = voteCount,
            movieGenresName = movieGenresName,
        )
    }


fun MovieDetailCache.toDomain(): MovieDetailDomain =
this.run {
        MovieDetailDomain(
            adult = adult,
            backdropPath = POSTER_PATH_URL + backdropPath,
            budget = budget,
            homepage = homepage,
            id = id,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = POSTER_PATH_URL + posterPath,
            releaseDate = releaseDate,
            runtime = runtime,
            voteAverage = voteAverage,
            voteCount = voteCount,
            movieGenresName = movieGenresName,
        )
    }

fun DetailResult.toDomain(): MovieDetailDomain =
    this.run {
        MovieDetailDomain(
            adult = adult,
            backdropPath = POSTER_PATH_URL + backdropPath,
            budget = budget,
            homepage = homepage,
            id = id,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = POSTER_PATH_URL + posterPath,
            releaseDate = releaseDate,
            runtime = runtime,
            voteAverage = voteAverage,
            voteCount = voteCount,
            movieGenresName = movieGenres.map { it.name },
        )
    }

fun MovieResult.toDomain(): MovieDomain =
    this.run {
        MovieDomain(
            backdropPath = POSTER_PATH_URL + backdropPath,
            id = id,
            posterPath = POSTER_PATH_URL + posterPath,
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            title = title
        )
    }