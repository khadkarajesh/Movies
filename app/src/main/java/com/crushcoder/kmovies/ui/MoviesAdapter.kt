package com.crushcoder.kmovies.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.crushcoder.kmovies.Movie
import com.crushcoder.kmovies.R
import com.crushcoder.kmovies.rest.FAILURE
import com.crushcoder.kmovies.rest.LOADING
import com.crushcoder.kmovies.rest.SUCCESS
import com.crushcoder.kmovies.rest.State
import kotlinx.android.synthetic.main.item_movie_adapter_view.view.*

class MoviesAdapter : PagedListAdapter<Movie, MovieVH>(COMPARATOR) {
    private val tag = MoviesAdapter::class.simpleName
    private lateinit var networkState: State
    private val VIEW_TYPE_LOADING = 0
    private val VIEW_TYPE_LOADED = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        return MovieVH.create(parent)
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        var movie = getItem(position)
        holder.itemView.tv_movie_adapter_title.text = movie?.title
        when (networkState) {
            is LOADING -> {
                Log.d(tag, "Loading")
            }

            is FAILURE -> {
                Log.d(tag, "Failure")
            }

            is SUCCESS -> {
                Log.d(tag, "Success")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (networkState == LOADING) VIEW_TYPE_LOADING else VIEW_TYPE_LOADED
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun updateUi(state: State) {
        networkState = state
        notifyDataSetChanged()
    }
}

class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup): MovieVH {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie_adapter_view, parent, false)
            return MovieVH(view)
        }
    }
}