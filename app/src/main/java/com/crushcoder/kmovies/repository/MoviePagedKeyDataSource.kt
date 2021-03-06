package com.crushcoder.kmovies.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.crushcoder.kmovies.model.Movie
import com.crushcoder.kmovies.rest.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MoviePagedKeyDataSource(var appService: AppService) : PageKeyedDataSource<Int, Movie>() {
    val tag = MoviePagedKeyDataSource::class.java.simpleName
    var networkState: MutableLiveData<State> = MutableLiveData()
    var totalPage = 0
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        Log.d(tag, "loadInitial ${params.placeholdersEnabled} ${params.requestedLoadSize}")
        GlobalScope.launch {
            async {
                networkState.postValue(LOADING)
                try {
                    var result = appService.getMovies("3d9f6ef05faa3072ee2caf7fb6870964", "en-US", 1).await()
                    totalPage = result.total_pages
                    Log.d("milliseconds", "" + result.results!![0].release_date.time)
                    callback.onResult(result.results.orEmpty(), null, 2)
                    networkState.postValue(SUCCESS)
                } catch (e: Throwable) {
                    networkState.postValue(FAILURE(e.localizedMessage))
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        Log.d(tag, "loadAfter ${params.key} ${params.requestedLoadSize}")
        GlobalScope.launch {
            async {
                try {
                    // networkState.postValue(LOADING)
                    var result = appService
                            .getMovies("3d9f6ef05faa3072ee2caf7fb6870964",
                                    "en-US",
                                    params.key)
                            .await()
                    callback.onResult(result.results.orEmpty(), params.key.inc())
                    //networkState.postValue(SUCCESS)
                } catch (e: Throwable) {
                    // networkState.postValue(FAILURE(e.localizedMessage))
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        Log.d(tag, "loadBefore ${params.key} ${params.requestedLoadSize}")
        GlobalScope.launch {
            async {
                try {
                    //networkState.postValue(LOADING)
                    var result = appService
                            .getMovies("3d9f6ef05faa3072ee2caf7fb6870964",
                                    "en-US",
                                    params.key.inc())
                            .await()
                    callback.onResult(result.results.orEmpty(), params.key.dec())
                    //networkState.postValue(SUCCESS)
                } catch (e: Throwable) {
                    // networkState.postValue(FAILURE(e.localizedMessage))
                }
            }
        }
    }
}