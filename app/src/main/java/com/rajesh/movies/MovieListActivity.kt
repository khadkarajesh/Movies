package com.rajesh.movies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        toast("hello kotlin from thailand")
    }

    fun toast(message: String, length: Int = Toast.LENGTH_LONG) = Toast.makeText(this, message, length).show()
}
