package com.senemyalin.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.senemyalin.movieapp.data.model.Movie
import com.senemyalin.movieapp.databinding.ItemMovieBinding

class MovieAdapter(
    private val onMovieClick: (Movie) -> Unit,
    private val onCheckboxClick: (Movie) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movieList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movieList[position])

    fun updateList(list: List<Movie>) {
        movieList.clear()
        movieList.addAll(list)
        notifyItemRangeChanged(0, list.size)
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                movieName.text = movie.name

                checkbox.isChecked = movie.isWatched

                root.setOnClickListener {
                    movie.let(onMovieClick)
                }

                checkbox.setOnClickListener {
                    movie.let(onCheckboxClick)
                }
            }
        }
    }
}