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
        imdb: String,
        details: String
    ) {
        watchedMovies.add(
            Movie(
                (watchedMovies.lastOrNull()?.id ?: 0) + 1,
                name,
                director,
                imdb,
                details,
                true
            )
        )
    }

    fun addFutureMovies(
        name: String,
        director: String,
        imdb: String,
        details: String
    ) {
        futureMovies.add(
            Movie(
                (futureMovies.lastOrNull()?.id ?: 0) + 1,
                name,
                director,
                imdb,
                details,
                false
            )
        )
    }

    fun removeWatchedMovie(id: Int) {
        var movie = watchedMovies.find { it.id == id }
        if (movie != null) {
            movie.isWatched = false
            watchedMovies.remove(movie)
            futureMovies.add(movie)
        }
    }

    fun deleteWatchedMovie(id: Int) {
        var movie = watchedMovies.find { it.id == id }

        if (movie != null) {
            watchedMovies.remove(movie)
        }
    }

    fun removeFutureMovie(id: Int) {
        var movie = futureMovies.find { it.id == id }

        if (movie != null) {
            movie.isWatched = true
            futureMovies.remove(movie)
            watchedMovies.add(movie)
        }
    }

    fun deleteFutureMovie(id: Int) {
        var movie = futureMovies.find { it.id == id }

        if (movie != null) {
            futureMovies.remove(movie)
        }
    }

}