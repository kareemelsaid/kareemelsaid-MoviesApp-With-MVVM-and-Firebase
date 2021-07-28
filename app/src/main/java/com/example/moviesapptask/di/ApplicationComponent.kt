package com.example.moviesapptask.di;

import com.example.moviesapptask.ui.activity.MoviesActivity
import com.example.moviesapptask.ui.activity.MoviesDetailsActivity
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ApplicationModule::class,
        RepositoriesModule::class,
        ViewModelModule::class,
        SharedPreferencesModule::class,
        ValidatorModule::class
    ]
)
@Singleton
interface ApplicationComponent {
    fun inject(target: MoviesActivity)
    fun inject(target: MoviesDetailsActivity)
}