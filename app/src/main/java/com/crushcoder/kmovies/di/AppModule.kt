package com.crushcoder.kmovies.di

import com.crushcoder.kmovies.rest.AppService
import com.crushcoder.kmovies.ui.list.MovieListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

var appModule = module {
    single { create(AppService::class.java) }
    viewModel { MovieListViewModel(get()) }
}