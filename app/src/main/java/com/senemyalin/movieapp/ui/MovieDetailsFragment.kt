package com.senemyalin.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.senemyalin.movieapp.R
import com.senemyalin.movieapp.common.toastMessage
import com.senemyalin.movieapp.common.viewBinding
import com.senemyalin.movieapp.data.source.Database
import com.senemyalin.movieapp.databinding.ActivityMainBinding
import com.senemyalin.movieapp.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private val binding by viewBinding(FragmentMovieDetailsBinding::bind)
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            name.text = args.movie.name
            imdb.text = args.movie.imdb.toString()
            director.text = args.movie.director
            details.text = args.movie.details

            btnDelete.setOnClickListener {
                onClickDelete()
            }

            btnBack.setOnClickListener {
                onClickBack()
            }
        }
    }

    private fun onClickBack() {
        var action: NavDirections = if (args.isWatchedMovie) {
            MovieDetailsFragmentDirections.actionMovieDetailsFragmentToWatchedMoviesFragment()
        } else {
            MovieDetailsFragmentDirections.actionMovieDetailsFragmentToFutureMoviesFragment()
        }
        findNavController().navigate(action)
    }

    private fun onClickDelete() {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Movie")
            .setMessage("Are you sure to delete this Movie?")
            .setPositiveButton("Yes") { _, _ ->
                if (args.isWatchedMovie) {

                    Database.deleteWatchedMovie(args.movie.id)
                    requireContext().toastMessage("Movie is deleted from Watched Movies!")
                } else {
                    Database.deleteFutureMovie(args.movie.id)
                    requireContext().toastMessage("Movie is deleted from Future Movies!")
                }
            }
            .setNegativeButton("No") { _, _ ->
                //DO NOTHING
            }
            .show()
    }
}