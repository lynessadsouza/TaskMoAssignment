package com.example.myapplication.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.moviesapp.DependencyInjection.Animations
import com.example.moviesapp.Model.Movie
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movie_details.*
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    @Inject
    lateinit var animation: Animations
    lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_movie_details)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        val movies = intent.getSerializableExtra("Movie") as Movie?
        startAnimation()
        Glide.with(this).load(movies?.image).into(binding.detailsThumbnail)
        binding.movieTitle.text = movies?.title
        binding.textView.text = movies?.description
        binding.year.text = movies?.year
        binding.movieRating.text = movies?.imDbRating
    }

    private fun startAnimation() {
        detailsThumbnail.startAnimation(animation.zoomAnimation)
        movieTitle.startAnimation(animation.ttb)
        textView.startAnimation(animation.ttb)
        year.startAnimation(animation.ttb)
        movieRating.startAnimation(animation.ttb)
        imageView2.startAnimation(animation.ttb)
    }
}