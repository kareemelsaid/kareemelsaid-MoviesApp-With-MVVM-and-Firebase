package com.example.moviesapptask.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapptask.R
import com.example.moviesapptask.base.BaseActivity
import com.example.moviesapptask.models.response.MoviesResponse
import com.example.moviesapptask.models.response.SetDataViewModel
import com.example.moviesapptask.ui.firebase.FirebaseStorageInterface
import com.example.moviesapptask.ui.viewmodel.MoviesViewModel
import com.example.moviesapptask.utilities.ViewState
import com.example.moviesapptask.utilities.builders.recyclerview.RecyclerViewBuilder
import com.example.moviesapptask.utilities.builders.recyclerview.RecyclerViewBuilderFactory
import com.example.moviesapptask.utilities.extensions.arraylist.toArrayList
import com.example.moviesapptask.utilities.extensions.toast.toast
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

enum class SendData(val key:String){
    ID("ID"),TITLE("TITLE"),POSTER_PATH("POSTER_PATH"),OVERVIEW("OVERVIEW"),USER_RATE("USER_RATE"),RELEASE_DATE("RELEASE_DATE")
}

class MoviesActivity : BaseActivity() {
    private lateinit var recyclerViewBuilder: RecyclerViewBuilder
    private lateinit var viewModel: MoviesViewModel
    private var pageNumber = 1
    private var totalPages = 1
    private var dataFromPaginate: ArrayList<SetDataViewModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MoviesViewModel::class.java]
        setOnClickListener()
        setupObservers()
        viewModel.getMovie(pageNumber)
    }

    private fun setOnClickListener() {
        recyclerViewBuilder =
            RecyclerViewBuilderFactory(moviesRecyclerView)
                .buildWithGridLayout(
                    isDataBindingEnabled = true,
                    orientation = LinearLayoutManager.VERTICAL,
                    columnCount = 3
                )
                .setPaginationEnabled(true)
                .onPaginate {
                    if (pageNumber == totalPages) {
                        recyclerViewBuilder.setPaginationEnabled(false)
                        return@onPaginate
                    }
                    pageNumber++
                    viewModel.getMovie(pageNumber)
                }
                .startLoading()

                .setOnItemClick { itemView, model, position ->
                    val intent = Intent(this,MoviesDetailsActivity::class.java)
                    intent.putExtra(SendData.ID.key,(model as SetDataViewModel).id)
                    intent.putExtra(SendData.TITLE.key, model.originalTitle)
                    intent.putExtra(SendData.POSTER_PATH.key, model.posterPath)
                    intent.putExtra(SendData.OVERVIEW.key, model.overview)
                    intent.putExtra(SendData.USER_RATE.key, model.voteAverage)
                    intent.putExtra(SendData.RELEASE_DATE.key, model.releaseDate)
                    startActivity(intent)
                }

    }

    private fun setupObservers() {
        viewModel.totalPages.observe(this, Observer {
            totalPages = it
        })
        viewModel.setDataViewState.observe(this, {
            when (it) {
                is ViewState.Loading -> {
                    loadingDialog.show()
                }
                is ViewState.Success -> {
                    loadingDialog.dismiss()
                    it.data.map {
                        dataFromPaginate.add(it)
                    }
                    recyclerViewBuilder.setViewItems(dataFromPaginate.map {
                        it.viewItem
                    }.toArrayList(),true)
                }
                is ViewState.Error -> {
                    toast(it.message.errorMessage)
                }
            }
        })
    }
}