package com.rajesh.movies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        val a = 1
        var result: Int
        result = sum(4, 5)
        println("sum  = ${result}")
    }

    fun sum(a: Int, b: Int) = a + b
}
