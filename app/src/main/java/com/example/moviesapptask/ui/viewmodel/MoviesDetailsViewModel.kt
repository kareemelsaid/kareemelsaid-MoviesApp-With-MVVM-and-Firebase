package com.example.moviesapptask.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapptask.ui.firebase.FirebaseStorage
import com.example.moviesapptask.ui.firebase.FirebaseStorageInterface
import javax.inject.Inject

class MoviesDetailsViewModel @Inject constructor(
    private val firebaseStorage: FirebaseStorage
) : ViewModel(), FirebaseStorageInterface {
    val moviesDetailsId = MutableLiveData<Int>()

    override fun updateOpenCount(id: Int) {
        moviesDetailsId.value = id
        firebaseStorage.updateOpenCount(id)
    }


}