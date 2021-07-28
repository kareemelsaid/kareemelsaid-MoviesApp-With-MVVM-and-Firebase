package com.example.moviesapptask.di;


import com.example.moviesapptask.utilities.managers.Validator
import com.example.moviesapptask.utilities.managers.ValidatorInterface
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ValidatorModule {

    @Binds
    @Singleton
    abstract fun bindValidator(validator: Validator): ValidatorInterface
}