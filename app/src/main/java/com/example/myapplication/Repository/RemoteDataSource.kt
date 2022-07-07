package com.example.moviesapp.Repository

import com.example.moviesapp.Model.MoviesResponse
import com.example.myapplication.Retrofit.ApiInterface
import retrofit2.Call
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
     val apiInterface: ApiInterface,
) {
     fun getApiMovies(): Call<MoviesResponse> {
        return apiInterface.getMovies()
    }

}