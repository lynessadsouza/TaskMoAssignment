package com.example.myapplication.View

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.example.moviesapp.Adapter.MovieListAdapter
import com.example.myapplication.R
import com.example.myapplication.ViewModel.RetrofitViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), MovieListAdapter.onItemClickListner {
    lateinit var recyclerAdapter: MovieListAdapter
    lateinit var recyclerAdapterh: MovieListAdapter
    private lateinit var viewModel: RetrofitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initRecyclerViewHorizontal()
        initRecyclerView()
        initViewModel()


        movieSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText == "") {
                    initViewModel()
                } else {
                    filterMovies(newText)
                }
                return true
            }
        })
    }
    private fun filterMovies(newText: String) {
        viewModel = ViewModelProvider(this).get(RetrofitViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, {
            if (it != null) {
                recyclerAdapter.setMovieList(it)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in getting List!!", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.filterMovies(newText)
    }


    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(RetrofitViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, {
            if (it != null) {
                recyclerAdapter.setMovieList(it)
                recyclerAdapter.notifyDataSetChanged()
                recyclerAdapterh.setMovieList(it)
                recyclerAdapterh.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in getting List!!", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeAPICall()
    }

    private fun initRecyclerViewHorizontal() {
        moviesRecyclerHorizontal.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        recyclerAdapterh = MovieListAdapter(this, this)
        moviesRecyclerHorizontal.adapter = recyclerAdapterh
    }

    private fun initRecyclerView() {
        moviesRecyclerVertical.layoutManager = GridLayoutManager(this, 3)
        recyclerAdapter = MovieListAdapter(this, this)
        moviesRecyclerVertical.adapter = recyclerAdapter
    }

    override fun onItemClick(position: Int) {
        Log.d("onItemClick", "onItemClick")
    }
}