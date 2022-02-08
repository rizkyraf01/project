package com.example.jetpackpro2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jetpackpro2.FactoryView
import com.example.jetpackpro2.adapter.FilmAdapter
import com.example.jetpackpro2.databinding.FragmentFilmBinding
import com.example.jetpackpro2.film.FilmViewModel
import com.example.jetpackpro2.utils.SortUtils.RANDOM
import com.example.jetpackpro2.utils.SortUtils.RANDOMIN
import com.example.jetpackpro2.vo.Resource.Companion.loading
import com.example.jetpackpro2.vo.Status


class FilmFragment : Fragment() {
    private lateinit var bindingFilm: FragmentFilmBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingFilm = FragmentFilmBinding.inflate(layoutInflater, container, false)
        return bindingFilm.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = FactoryView.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[FilmViewModel::class.java]

        val moviesAdapter = FilmAdapter()
        viewModel.getMovies(RANDOM).observe(viewLifecycleOwner, { movies ->
            when(movies.status){
                Status.SUCCESS -> {
                    moviesAdapter.submitList(movies.data)
                    moviesAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    Toast.makeText(context, "Something Wrong..", Toast.LENGTH_SHORT).show()
                }
            }
        })

        with(bindingFilm.recycleViewFilm){
            layoutManager = GridLayoutManager(context,3)

            this.adapter = moviesAdapter
        }


    }
    }

