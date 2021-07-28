package com.example.moviesapptask.utilities

import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

abstract class MultiPartRepresentable {

    fun toMultiPartArrayList(): ArrayList<MultipartBody.Part> {
        val multiPartArrayList = ArrayList<MultipartBody.Part>()

        val gson = Gson()
        val jsonString = gson.toJson(this)
        val type = object : TypeToken<Map<String, String>>() {}.type
        val linkedTreeMap = gson.fromJson(jsonString, type) as LinkedTreeMap<String, String>

        for ((key, value) in linkedTreeMap) {
            multiPartArrayList.add(convertToMultiPartValue(key, value))
        }

        return multiPartArrayList
    }

    private fun <T> convertToMultiPartValue(key: String, value: T): MultipartBody.Part {
        return MultipartBody.Part.createFormData(key, value.toString())
    }
}

fun convertPhotosToMultiPartValue(key: String, path: String): MultipartBody.Part {
    val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), File(path))
    return MultipartBody.Part.createFormData(key, File(path).name, requestFile)
}

fun <T> convertToMultiPartValue(key: String, value: T): MultipartBody.Part {
    return MultipartBody.Part.createFormData(key, value.toString())
}