package com.crushcoder.kmovies.ui

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.crushcoder.kmovies.rest.AppService
import com.crushcoder.kmovies.model.Movie
import com.crushcoder.kmovies.rest.State

class MovieDataSourceFactory(var service: AppService) : DataSource.Factory<Int, Movie>() {
    private val sourceLiveData = MutableLiveData<MoviePagedKeyDataSource>()
    lateinit var source: MoviePagedKeyDataSource
    override fun create(): DataSource<Int, Movie> {
        val source = MoviePagedKeyDataSource(appService = service)
        sourceLiveData.postValue(source)
        return source
    }

    fun networkState(): MutableLiveData<State> {
        return source.networkState
    }
}

