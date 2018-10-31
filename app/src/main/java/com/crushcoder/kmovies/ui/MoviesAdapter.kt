package com.crushcoder.kmovies.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.crushcoder.kmovies.R
import com.crushcoder.kmovies.model.Movie
import kotlinx.android.synthetic.main.item_movie_adapter_view.view.*

class MoviesAdapter : PagedListAdapter<Movie, MovieVH>(COMPARATOR) {
    private val tag = MoviesAdapter::class.java.simpleName
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        return MovieVH.create(parent)
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        var movie = getItem(position)
        holder.itemView.tv_movie_adapter_title.text = movie?.title
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