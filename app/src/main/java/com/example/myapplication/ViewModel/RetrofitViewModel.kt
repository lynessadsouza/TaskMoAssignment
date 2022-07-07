package com.example.myapplication.ViewModel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.Model.Movie
import com.example.moviesapp.Model.MoviesResponse
import com.example.moviesapp.Repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "RetrofitViewModel"

@HiltViewModel
class RetrofitViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    var liveDataList: MutableLiveData<List<Movie>> = MutableLiveData()
    val movieItemm: MutableList<Movie> = mutableListOf()

    fun getLiveDataObserver(): MutableLiveData<List<Movie>> {
        return liveDataList
    }

    fun filterMovies(filteredMovies: String) {
        val mov: MutableList<Movie> = mutableListOf()
        for (item in movieItemm) {
            if (item.title?.lowercase()?.contains(filteredMovies.lowercase()) == true) {
                mov.add(item)
                Log.d("Movie", "$mov")
            }
        }
        liveDataList.postValue(mov)
    }

    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.remote.getApiMovies()
            response.enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    Log.d("TAG", "${response.body()}")
                    val movies: MutableList<Movie> = mutableListOf()
                    response.body()?.items?.let { movieItems ->
                        for (movieItem in movieItems) {
                            var movie: Movie = Movie()
                            movie.id = movieItem.id
                            movie.title = movieItem.title
                            movie.imDbRating = movieItem.imDbRating
                            movie.image = movieItem.image
                            movie.fullTitle = movieItem.fullTitle
                            movie.imDbRatingCount = movieItem.imDbRatingCount
                            movie.year = movieItem.year
                            movie.index = movieItem.index
                            movie.description = movieItem.description
                            movies.add(movie)
                            movieItemm.add(movie)

                        }
                    }
                    liveDataList.postValue(movies)
                }
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure")
                }
            })
        }
    }
}


