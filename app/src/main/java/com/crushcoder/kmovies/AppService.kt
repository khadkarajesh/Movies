package com.crushcoder.kmovies

import com.crushcoder.kmovies.rest.retrofit.Page
import com.crushcoder.kmovies.rest.retrofit.Pageable
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {
    @Page
    @GET("movie/popular")
    fun getMovies(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("page") page: Int): Deferred<Pageable<List<Movie>>>

    @GET("movie/popular")
    fun getMoviesList(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("page") page: Int): Deferred<List<Movie>>
}