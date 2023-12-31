package com.example.moviesapp.data.repository

import com.example.moviesapp.data.cashe.source.MovieCacheDataSource
import com.example.moviesapp.data.cloude.source.MovieCloudDataSource
import com.example.moviesapp.domain.MovieRepository
import com.example.moviesapp.domain.models.ActorsDomain
import com.example.moviesapp.domain.models.MovieDetailDomain
import com.example.moviesapp.domain.models.MovieDomain
import com.example.moviesapp.domain.models.ReviewsDomain
import com.example.moviesapp.presentation.mappers.toCache
import com.example.moviesapp.presentation.mappers.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val cloudDataSource: MovieCloudDataSource,
    private val cacheDataSource: MovieCacheDataSource
) :
    MovieRepository {

    override suspend fun addNewMovie(movie: MovieDetailDomain) {
        cacheDataSource.addNewMovie(movie = movie.toCache())
    }

    override suspend fun deleteMovieById(movieId: Int) {
        cacheDataSource.deleteMovieById(movieId)
    }

    override  fun fetchAllSavedMovies(): Flow<List<MovieDetailDomain>> {
        return cacheDataSource.fetchSavedMovies().map { it.map { it.toDomain() }}
    }

    override suspend fun fetchPopularMovie(): List<MovieDomain> {
        return cloudDataSource.fetchPopularMovie().map { it.toDomain() }
    }

    override suspend fun fetchTopRatedMovie(): List<MovieDomain> {
        return cloudDataSource.fetchTopRatedMovie().map { it.toDomain() }
    }

    override suspend fun fetchUpcomingMovie(): List<MovieDomain> {
        return cloudDataSource.fetchUpcomingMovie().map { it.toDomain() }
    }

    override suspend fun fetchNowPlayingMovie(): List<MovieDomain> {
        return cloudDataSource.fetchNowPlayingMovie().map { it.toDomain() }
    }

    override suspend fun fetchSearchMovie(query: String): List<MovieDomain> {
        return cloudDataSource.fetchSearchMovie(query).map { it.toDomain() }
    }

    override suspend fun fetchMovieById(movieId: Int): MovieDetailDomain? {
        return cloudDataSource.fetchMovieById(movieId)?.toDomain()
    }

    override fun isMovieSavedFlow(movieId: Int): Flow<Boolean> {
        return cacheDataSource.isMovieSavedFloe(movieId)
    }

    override suspend fun fetchMoviePeoples(movieId: Int): ActorsDomain {
        return cloudDataSource.fetchMoviePeoples(movieId).toDomain()
    }

    override suspend fun fetchMovieReviews(movieId: Int): List<ReviewsDomain> {
        return cloudDataSource.fetchMovieReviews(movieId).map { it.toDomain()}
    }
}