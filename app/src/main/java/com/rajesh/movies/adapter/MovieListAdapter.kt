package com.rajesh.movies.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rajesh.movies.R


class MovieListAdapter(val context: Context, val movies: List<String>) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieListAdapter.MovieListViewHolder =
            MovieListViewHolder(LayoutInflater.from(context).inflate(R.layout.single_movie_item, parent, false))

    override fun onBindViewHolder(holder: MovieListAdapter.MovieListViewHolder?, position: Int) {
        holder!!.textView.text = movies[position]
    }

    override fun getItemCount(): Int = movies.size

    class MovieListViewHolder : RecyclerView.ViewHolder {
        val textView: TextView

        constructor(itemView: View) : super(itemView) {
            textView = itemView.findViewById(R.id.text_view_movie_title) as TextView
        }
    }

}