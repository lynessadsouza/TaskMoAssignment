package com.example.moviesapp.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.Model.Movie

import com.example.myapplication.R
import com.example.myapplication.View.HomeActivity

class MovieListAdapter (
    var mlistner: HomeActivity,
    private val context: Context
) : RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {
    private var movieList: List<Movie>? = null


    fun setMovieList(movieList: List<Movie>?) {
        this.movieList = movieList
    }
    fun setMovie(movieList: Movie) {
        this.movieList = listOf(movieList)
    }
    interface onItemClickListner {
        fun onItemClick(position: Int)
    }



    class MyViewHolder(view: View, listner: onItemClickListner) : RecyclerView.ViewHolder(view) {
        val movieTitle: TextView = view.findViewById(R.id.movieTitle)
        val movieYear: TextView = view.findViewById(R.id.movieYear)
        val movieThumbnail: ImageView = view.findViewById(R.id.movieThumbnail)
        val movieRating:RatingBar=view.findViewById(R.id.movieRating)

        init {
            itemView.setOnClickListener {
                listner.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false), mlistner
        )

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movieListData = movieList?.get(position)
        holder.movieTitle.text=movieListData?.title
        Glide.with(context).load(movieListData?.image).into(holder.movieThumbnail)
        holder.movieYear.text=movieListData?.year
        val rating : Float? =(movieListData?.imDbRating?.toFloat()?.div(2))
        if (rating != null) {
            holder.movieRating.rating =rating
        }
    }

    override fun getItemCount(): Int {
        if (movieList == null)
            return 0
        else return movieList?.size!!
    }
}