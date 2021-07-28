package com.example.moviesapptask.models.response


import com.example.moviesapptask.ui.viewitem.MovieViewItem
import com.example.moviesapptask.utilities.builders.recyclerview.AbstractViewItem
import com.example.moviesapptask.utilities.builders.recyclerview.ViewItemRepresentable
data class MoviesResponse(
    val page: Int,
    var results: List<Result> = listOf(),
    val total_pages: Int,
    val total_results: Int
) {
    data class Result(
        val id: Int,
        val original_title: String,
        val overview: String,
        val poster_path: String,
        val release_date: String,
        val title: String,
        val vote_average: Double,
        val vote_count: Int
    )
}


class SetDataViewModel(
    data: MoviesResponse.Result
) : ViewItemRepresentable {
    override val viewItem: AbstractViewItem
        get() = MovieViewItem(
            this
        )
    var id = data.id
    var posterPath = "http://image.tmdb.org/t/p/w500/" + data.poster_path
    var originalTitle = data.original_title
    var overview = data.overview
    var voteAverage = data.vote_average
    var releaseDate = data.release_date
}