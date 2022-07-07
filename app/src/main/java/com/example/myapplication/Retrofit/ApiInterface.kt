package com.example.myapplication.Retrofit
import com.example.moviesapp.Model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
 //@GET("k_qk9qiyxa/ls004285275")
 @GET("k_66aq68k3/ls004285275")
     fun getMovies(): Call<MoviesResponse>
}
//https://imdb-api.com/en/API/IMDbList/k_qk9qiyxa/ls004285275
