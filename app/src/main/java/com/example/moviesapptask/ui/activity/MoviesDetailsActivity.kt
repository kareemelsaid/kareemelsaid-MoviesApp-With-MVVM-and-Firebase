package com.example.moviesapptask.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviesapptask.R
import com.example.moviesapptask.base.BaseActivity
import com.example.moviesapptask.ui.viewmodel.MoviesDetailsViewModel
import com.example.moviesapptask.utilities.extensions.imageview.load
import com.example.moviesapptask.utilities.extensions.toast.toast
import kotlinx.android.synthetic.main.activity_movies_details.*

class MoviesDetailsActivity : BaseActivity() {
    private lateinit var viewModel: MoviesDetailsViewModel
    private var id: Int? = 0
    private var userRate: Double? = 0.0
    private var title: String? = ""
    private var posterPath: String? = ""
    private var overview: String? = ""
    private var releaseDate: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MoviesDetailsViewModel::class.java]
        id = intent.extras?.getInt(SendData.ID.key)
        title = intent.extras?.getString(SendData.TITLE.key)
        posterPath = intent.extras?.getString(SendData.POSTER_PATH.key)
        overview = intent.extras?.getString(SendData.OVERVIEW.key)
        userRate = intent.extras?.getDouble(SendData.USER_RATE.key)
        releaseDate = intent.extras?.getString(SendData.RELEASE_DATE.key)
        setData()
        viewModel.updateOpenCount(id!!)
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        titleTextView.text = title
        posterPath?.let { posterImageView.load(it) }
        overviewTextView.text = overview
        userRateTextView.text = "${resources.getString(R.string.user_rate)}  ${userRate}"
        releaseDateTextView.text = "${resources.getString(R.string.release_date)}  ${releaseDate}"
    }
}