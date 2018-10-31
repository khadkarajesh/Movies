package com.crushcoder.kmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.crushcoder.kmovies.ui.MovieDataSourceFactory

class MainViewModel(appService: AppService) : BaseViewModel() {
    override fun onActivityCreated() {

    }

    var movieList: LiveData<PagedList<Movie>> = MutableLiveData()
    private var movieDataSourceFactory = MovieDataSourceFactory(appService)

    init {
        var pageListConfig = PagedList.Config
                .Builder()
                .setPageSize(20)
                .setPrefetchDistance(20)
                .setEnablePlaceholders(true)
                .build()

        movieList = LivePagedListBuilder(movieDataSourceFactory, pageListConfig).build()
    }


}

