package com.crushcoder.kmovies

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.crushcoder.kmovies.base.activity.BaseActivity
import com.crushcoder.kmovies.ui.MoviesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class) {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var adapter = MoviesAdapter()
        main_rv_movies.layoutManager = LinearLayoutManager(this)
        main_rv_movies.adapter = adapter
        viewModel.movieList.observe(this, Observer { adapter.submitList(it) })
    }
}
