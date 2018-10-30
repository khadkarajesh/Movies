package com.crushcoder.kmovies.di

import com.crushcoder.kmovies.AppService
import com.crushcoder.kmovies.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

var appModule = module {
    single { create(AppService::class.java) }
    viewModel { MainViewModel(get()) }
}