package com.dicoding.jetpacksubmission3.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpacksubmission3.databinding.FragmentMoviesBinding
import com.dicoding.jetpacksubmission3.utils.SortUtils.NEWEST_MOVIE
import com.dicoding.jetpacksubmission3.utils.SortUtils.RANDOM
import com.dicoding.jetpacksubmission3.viewmodel.ViewModelFactory
import com.dicoding.jetpacksubmission3.vo.Status

class MoviesFragment : Fragment() {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

            val moviesAdapter = MoviesAdapter()
            viewModel.getMovies(RANDOM).observe(viewLifecycleOwner, { movies ->
            when(movies.status){
                Status.LOADING -> loading(true)
                Status.SUCCESS -> {
                    loading(false)
                    moviesAdapter.submitList(movies.data)
                    moviesAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    loading(false)
                    Toast.makeText(context, "Something Wrong..", Toast.LENGTH_SHORT).show()
                }
            }
        })

            with(fragmentMoviesBinding.listMovies){
                layoutManager = GridLayoutManager(context,3)

                this.adapter = moviesAdapter
            }

            val moviesTrendAdapter = MoviesTrendAdapter()
            viewModel.getMovies(NEWEST_MOVIE).observe(viewLifecycleOwner, { moviesTrend ->
                when(moviesTrend.status){
                    Status.LOADING -> loading(true)
                    Status.SUCCESS -> {
                        loading(false)
                        moviesTrendAdapter.submitList(moviesTrend.data)
                        moviesTrendAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        loading(false)
                        Toast.makeText(context, "Something Wrong..", Toast.LENGTH_SHORT).show()
                    }
                }
            })

            with(fragmentMoviesBinding.trendMovies){
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                this.adapter = moviesTrendAdapter
            }
    }

    private fun loading(load: Boolean) {
        when (load) {
            true -> fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
            false -> fragmentMoviesBinding.progressBar.visibility = View.GONE
        }
    }
}