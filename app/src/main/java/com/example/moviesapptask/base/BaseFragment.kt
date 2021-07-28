package com.example.moviesapptask.base

import android.app.Activity
import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapptask.app.UserApplication
import com.example.moviesapptask.di.ApplicationComponent
import com.example.moviesapptask.utilities.builders.LoadingDialog
import javax.inject.Inject

open class BaseFragment : Fragment() {

    val component: ApplicationComponent
        get() = (activity?.application as UserApplication).component

    val loadingDialog by lazy {
        val dialog = LoadingDialog(activity as Activity).build()
        dialog
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appResources: Resources
}