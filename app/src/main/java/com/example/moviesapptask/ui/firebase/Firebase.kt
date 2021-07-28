package com.example.moviesapptask.ui.firebase

import android.util.Log
import com.example.moviesapptask.models.response.MovieInfo
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

interface FirebaseStorageInterface {
    fun updateOpenCount(id: Int)
}

class FirebaseStorage @Inject constructor(): FirebaseStorageInterface {

    private val firestore = Firebase.firestore

    override fun updateOpenCount(id: Int) {
        firestore
            .collection("movies")
            .document(id.toString())
            .get()
            .addOnSuccessListener { documentReference ->
                documentReference.data?.let {
                    updateOpenedCount(id)
                    return@addOnSuccessListener
                }

                setOpenedCount(1, id)
            }
            .addOnFailureListener { error ->
                Log.e("Error", error.toString())
            }
    }

    private fun setOpenedCount(count: Int, id: Int) {
        firestore
            .collection("movies")
            .document(id.toString())
            .set(hashMapOf("opened" to count))
            .addOnSuccessListener { }
            .addOnFailureListener { error ->
                Log.e("error", error.toString())
            }
    }

    private fun updateOpenedCount(id: Int) {
        firestore
            .collection("movies")
            .document(id.toString())
            .get()
            .addOnSuccessListener { documentReference ->
                documentReference.data?.let {
                    val openCount = it["opened"] as? Long
                    openCount?.toInt()?.let {
                        val updatedOpenCount = it + 1
                        setOpenedCount(updatedOpenCount, id)
                    }
                }
            }
            .addOnFailureListener { error ->
                Log.e("error", error.toString())
            }

    }
}