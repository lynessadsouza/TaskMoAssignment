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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        val movies = intent.getSerializableExtra("Movie") as Movie?
        startAnimation()




        Glide.with(this).load(movies?.image).into(binding.detailsThumbnail)
        binding.movieTitle.text = movies?.title
        binding.textView.text = movies?.description
        binding.year.text = movies?.year



        val rating : Float? =(movies?.imDbRating?.toFloat()?.div(2))
        if (rating != null) {
            binding.movieRating.rating =rating
        }
    }

    private fun startAnimation() {
        binding.detailsThumbnail.startAnimation(animation.zoomAnimation)
        binding.movieTitle.startAnimation(animation.ttb)
        binding.textView.startAnimation(animation.ttb)
        binding.year.startAnimation(animation.ttb)
        binding.movieRating.startAnimation(animation.ttb)
        binding.desc.startAnimation(animation.ttb)
        binding.movieRating.startAnimation(animation.ttb)
    }
}