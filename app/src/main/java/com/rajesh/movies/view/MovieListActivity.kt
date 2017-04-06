package com.rajesh.movies.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.rajesh.movies.R
import com.rajesh.movies.adapter.MovieListAdapter


class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        val recyclerViewMovieList = findViewById(R.id.recycler_view_movie_list) as RecyclerView
        recyclerViewMovieList.layoutManager = LinearLayoutManager(this)
        var myList: MutableList<String> = mutableListOf(String())
        myList.add("hello")
        myList.add("rajesh")
        myList.add("how")
        myList.add("is")

        recyclerViewMovieList.adapter = MovieListAdapter(this, myList)
    }

    fun toast(message: String, length: Int = Toast.LENGTH_LONG) = Toast.makeText(this, message, length).show()

}
