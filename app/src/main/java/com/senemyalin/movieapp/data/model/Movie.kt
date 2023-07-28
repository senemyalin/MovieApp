package com.senemyalin.movieapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var id: Int,
    val name: String,
    val director: String,
    val imdb: String,
    val details: String
) : Parcelable
