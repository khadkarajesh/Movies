package com.rajesh.movies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MovieActivity : AppCompatActivity(), Repository {
    override fun onSuccess(a: String) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailure(b: String) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        val movie = Movie()
        movie.cost = 34.0
        movie.rating = 8

        ("message ${movie.rating}")

    }

    fun sum(a: Int, b: Int) = a + b
}
