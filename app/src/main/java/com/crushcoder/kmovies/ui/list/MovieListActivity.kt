package com.crushcoder.kmovies.ui.list

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crushcoder.kmovies.R
import com.crushcoder.kmovies.base.activity.BaseActivity
import com.crushcoder.kmovies.ui.MoviesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MovieListActivity : BaseActivity<MovieListViewModel>(MovieListViewModel::class) {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var adapter = MoviesAdapter()
        main_rv_movies.layoutManager = (LinearLayoutManager(this) as RecyclerView.LayoutManager?)!!
        main_rv_movies.adapter = adapter
        viewModel.movieList.observe(this, Observer { adapter.submitList(it) })

        //print("rajesh".filter { it in 'a'..'z' })
    }

    private fun calculate(s: Char): Boolean {
        return s in 'a'..'z'
    }


    fun twoAndThree(operation: (Int, Int) -> Int) {
        val result = operation(2, 3)
        println("The result is $result")
    }

    private fun String.filter(predicate: (Char) -> Boolean): String {
        var builder = StringBuilder()
        for (index in 0 until this.length - 1) {
            val element = get(index)
            if (predicate(element)) {
                builder.append(element)
            }
        }
        return builder.toString()
    }
}
