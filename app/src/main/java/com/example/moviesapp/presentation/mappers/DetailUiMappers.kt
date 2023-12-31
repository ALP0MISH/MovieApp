package com.example.moviesapp.presentation.mappers

import com.example.moviesapp.data.cashe.models.MovieDetailCache
import com.example.moviesapp.data.cloude.models.all_movies_remote.MovieResult
import com.example.moviesapp.data.cloude.models.credits_remote.ActorsResponse
import com.example.moviesapp.data.cloude.models.credits_remote.PoepleCloude
import com.example.moviesapp.data.cloude.models.detail_remote.DetailResult
import com.example.moviesapp.data.cloude.models.reviews_remote.ReviewsCloud
import com.example.moviesapp.domain.models.ActorsDomain
import com.example.moviesapp.domain.models.MovieDetailDomain
import com.example.moviesapp.domain.models.MovieDomain
import com.example.moviesapp.domain.models.PeopleDomain
import com.example.moviesapp.domain.models.ReviewsDetailDomain
import com.example.moviesapp.domain.models.ReviewsDomain
import com.example.moviesapp.presentation.models.MovieUi
import java.time.ZonedDateTime

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
            backdropPath = backdropPath,
            budget = budget,
            homepage = homepage,
            id = id,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
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

fun List<MovieDomain>.toDomain(): List<MovieUi> {
    return this.map { movieDomain ->
        MovieUi(
            backdropPath = POSTER_PATH_URL + movieDomain.backdropPath ?: "",
            id = movieDomain.id,
            posterPath = POSTER_PATH_URL + movieDomain.posterPath ?: "",
            releaseDate = movieDomain.releaseDate,
            voteAverage = movieDomain.voteAverage,
            title = movieDomain.title,
            runtime = movieDomain.runtime
        )
    }
}

fun MovieResult.toDomain(): MovieDomain =
    this.run {
        MovieDomain(
            backdropPath = POSTER_PATH_URL + backdropPath ?: "",
            id = id,
            posterPath = POSTER_PATH_URL + posterPath ?: "",
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            title = title,
            runtime = runtime,
        )
    }

fun PoepleCloude.toDomain() = this.run {
    PeopleDomain(
        cast_id = cast_id,
        credit_id = credit_id,
        id = id,
        name = name,
        original_name = original_name,
        popularity = popularity,
        profile_path =  POSTER_PATH_URL + profile_path
    )
}

fun ActorsResponse.toDomain() = this.run {
    ActorsDomain(
        id = id,
        poepleCloude = poepleCloude.map { it.toDomain() },
        crewCloud = crewCloud.map { it.toDomain() }
    )
}

fun ReviewsCloud.toDomain(): ReviewsDomain = this.run {
    ReviewsDomain(author = author,
        id = id,
        content = content,
        reviewsDetails = ReviewsDetailDomain(
            avatar_path = POSTER_PATH_URL + reviewsDetailCloud.avatar_path,
            name = reviewsDetailCloud.name,
            username = reviewsDetailCloud.username,
            rating = reviewsDetailCloud.rating,
        ),
        created_at = ZonedDateTime.parse(created_at).toLocalDateTime()
    )
}