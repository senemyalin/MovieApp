package com.senemyalin.movieapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.senemyalin.movieapp.R
import com.senemyalin.movieapp.common.viewBinding
import com.senemyalin.movieapp.data.model.Movie
import com.senemyalin.movieapp.data.source.Database
import com.senemyalin.movieapp.databinding.FragmentMoviesBinding


class WatchedMoviesFragment : Fragment(R.layout.fragment_movies) {

    private val binding by viewBinding(FragmentMoviesBinding::bind)

    private val movieAdapter = MovieAdapter(::onMovieClick, ::onCheckboxClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData(Database.getWatchedMovies())

    }

    private fun setData(list: List<Movie>) {
        binding.rvMovies.adapter = movieAdapter
        movieAdapter.updateList(list)
    }

    private fun onMovieClick(movie: Movie) {
        val action =
            WatchedMoviesFragmentDirections.actionWatchedMoviesFragmentToMovieDetailsFragment(
                movie
            )
        findNavController().navigate(action)
    }

    private fun onCheckboxClick(movie: Movie) {
        Database.removeWatchedMovie(movie.id)
        setData(Database.getWatchedMovies())
    }
}