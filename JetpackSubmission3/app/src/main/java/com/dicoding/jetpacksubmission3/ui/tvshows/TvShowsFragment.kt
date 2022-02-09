package com.dicoding.jetpacksubmission3.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpacksubmission3.databinding.FragmentTvShowsBinding
import com.dicoding.jetpacksubmission3.utils.SortUtils.NEWEST_TV
import com.dicoding.jetpacksubmission3.utils.SortUtils.RANDOM
import com.dicoding.jetpacksubmission3.viewmodel.ViewModelFactory
import com.dicoding.jetpacksubmission3.vo.Status

class TvShowsFragment : Fragment() {

    private lateinit var fragmentTvShowsBinding: FragmentTvShowsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowsViewModel::class.java]

            val tvAdapter = TvShowsAdapter()
            viewModel.getTvShow(RANDOM).observe(viewLifecycleOwner,{ tvShow ->
                when(tvShow.status){
                    Status.LOADING -> loading(true)
                    Status.SUCCESS -> {
                        loading(false)
                        tvAdapter.submitList(tvShow.data)
                        tvAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        loading(false)
                        Toast.makeText(context, "Something Wrong..", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            with(fragmentTvShowsBinding.listTv){
                layoutManager = GridLayoutManager(context,3)

                this.adapter = tvAdapter
            }

            val tvPopularAdapter = TvPopularAdapter()
            viewModel.getTvShow(NEWEST_TV).observe(viewLifecycleOwner,{ tvPopular ->
                when(tvPopular.status){
                    Status.LOADING -> loading(true)
                    Status.SUCCESS -> {
                        loading(false)
                        tvPopularAdapter.submitList(tvPopular.data)
                        tvPopularAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        loading(false)
                        Toast.makeText(context, "Something Wrong..", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            with(fragmentTvShowsBinding.popularSeries){
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                this.adapter = tvPopularAdapter
            }
        }
    }

    private fun loading(load: Boolean){
        when(load){
            true -> fragmentTvShowsBinding.progressBar.visibility = View.VISIBLE
            false -> fragmentTvShowsBinding.progressBar.visibility = View.GONE
        }
    }
}