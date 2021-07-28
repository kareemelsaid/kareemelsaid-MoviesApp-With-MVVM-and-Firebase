package com.example.moviesapptask.di;


import com.example.moviesapptask.utilities.managers.SharedPreferencesManager
import com.example.moviesapptask.utilities.managers.SharedPreferencesManagerInterface
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
abstract class SharedPreferencesModule {

    @Binds
    @Singleton
    abstract fun bindSharedPreferences(sharedPreferencesManager: SharedPreferencesManager): SharedPreferencesManagerInterface
}