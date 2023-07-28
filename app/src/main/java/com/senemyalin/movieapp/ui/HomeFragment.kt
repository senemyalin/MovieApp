package com.senemyalin.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.senemyalin.movieapp.R
import com.senemyalin.movieapp.common.toastMessage
import com.senemyalin.movieapp.common.viewBinding
import com.senemyalin.movieapp.data.source.Database
import com.senemyalin.movieapp.databinding.FragmentHomeBinding
import com.senemyalin.movieapp.databinding.FragmentMovieDetailsBinding


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            layoutAddMovie.setOnClickListener {
                if (!editTxtMovieName.text.isNullOrEmpty() &&
                    !editTxtDirector.text.isNullOrEmpty() &&
                    !editTxtImdb.text.isNullOrEmpty() &&
                    !editTxtDetails.text.isNullOrEmpty()
                ) {
                    Database.addFutureMovies(
                        editTxtMovieName.text.toString(),
                        editTxtDirector.text.toString(),
                        editTxtImdb.text.toString(),
                        editTxtDetails.text.toString()
                    )

                    context?.toastMessage("Your movie is added to Future Movies")
                }
            }
        }
    }
}