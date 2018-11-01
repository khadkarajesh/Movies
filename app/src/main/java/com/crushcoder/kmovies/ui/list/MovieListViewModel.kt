package com.crushcoder.kmovies.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.crushcoder.kmovies.base.viewmodel.BaseViewModel
import com.crushcoder.kmovies.model.Movie
import com.crushcoder.kmovies.repository.MovieDataSourceFactory
import com.crushcoder.kmovies.rest.AppService

class MovieListViewModel(var appService: AppService) : BaseViewModel() {
    var movieList: LiveData<PagedList<Movie>> = MutableLiveData()
    private var movieDataSourceFactory = MovieDataSourceFactory(appService)

    override fun onActivityCreated() {

    }

    init {
        var pageListConfig = PagedList.Config
                .Builder()
                .setPageSize(20)
                .setPrefetchDistance(20)
                .setEnablePlaceholders(true)
                .build()

        movieList = LivePagedListBuilder(movieDataSourceFactory, pageListConfig).build()
    }

    fun fetchData() {
        execute(appService.getMovies("", "", 1))
        {
            Log.d("yeah list", "" + it!!.total_results)
        }
    }
}

