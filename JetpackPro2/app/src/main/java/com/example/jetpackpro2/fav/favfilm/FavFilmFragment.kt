package com.example.jetpackpro2.fav.favfilm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackpro2.FactoryView
import com.example.jetpackpro2.R
import com.example.jetpackpro2.databinding.FragmentFavFilmBinding
import com.google.android.material.snackbar.Snackbar


class FavFilmFragment : Fragment() {
    private lateinit var fragmentMovieFavBinding: FragmentFavFilmBinding
    private lateinit var viewModel: FavFilmViewModel
    private lateinit var moviesAdapter: FavFilmAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMovieFavBinding = FragmentFavFilmBinding.inflate(layoutInflater)
        return (fragmentMovieFavBinding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentMovieFavBinding.rvMovieFav)
        val factory = FactoryView.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavFilmViewModel::class.java]

        moviesAdapter = FavFilmAdapter()
        viewModel.getMoviesFav().observe(viewLifecycleOwner, {movieFav ->
            moviesAdapter.submitList(movieFav)
        })

        with(fragmentMovieFavBinding.rvMovieFav){
            layoutManager = LinearLayoutManager(context)
            adapter = moviesAdapter
        }

    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val courseEntity = moviesAdapter.getSwipedData(swipedPosition)
                courseEntity?.let { viewModel.setMoviesFav(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    courseEntity?.let { viewModel.setMoviesFav(it) }
                }
                snackbar.show()
            }
        }
    })



}