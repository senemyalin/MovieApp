package com.senemyalin.movieapp.data.source

import com.senemyalin.movieapp.data.model.Movie

object Database {
    private val watchedMovies = mutableListOf<Movie>()
    private val futureMovies = mutableListOf<Movie>()

    fun getWatchedMovies() = watchedMovies
    fun getFutureMovies() = futureMovies

    fun addWatchedMovies(
        name: String,
        director: String,
        imdb: Float,
        details: String
    ) {
        watchedMovies.add(
            Movie(
                (watchedMovies.lastOrNull()?.id ?: 0) + 1,
                name,
                director,
                imdb,
                details
            )
        )
    }

    fun addFutureMovies(
        name: String,
        director: String,
        imdb: Float,
        details: String
    ) {
        futureMovies.add(
            Movie(
                (futureMovies.lastOrNull()?.id ?: 0) + 1,
                name,
                director,
                imdb,
                details
            )
        )
    }

    fun removeWatchedMovie(id: Int) {
        var movie = watchedMovies.find { it.id == id }

        if (movie != null) {
            watchedMovies.remove(movie)
            futureMovies.add(movie)
        }
    }

    fun removeFutureMovie(id: Int) {
        var movie = futureMovies.find { it.id == id }

        if (movie != null) {
            futureMovies.remove(movie)
            watchedMovies.add(movie)
        }
    }

}