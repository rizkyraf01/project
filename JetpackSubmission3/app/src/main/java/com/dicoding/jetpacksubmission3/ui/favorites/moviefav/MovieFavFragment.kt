package com.dicoding.jetpacksubmission3.ui.favorites.moviefav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.jetpacksubmission3.R
import com.dicoding.jetpacksubmission3.databinding.FragmentMovieFavBinding
import com.dicoding.jetpacksubmission3.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MovieFavFragment : Fragment() {

    private lateinit var fragmentMovieFavBinding: FragmentMovieFavBinding
    private lateinit var viewModel: MovieFavViewModel
    private lateinit var moviesAdapter: MovieFavAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMovieFavBinding = FragmentMovieFavBinding.inflate(layoutInflater)
        return (fragmentMovieFavBinding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(fragmentMovieFavBinding.rvMovieFav)

        loading(true)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MovieFavViewModel::class.java]

        moviesAdapter = MovieFavAdapter()
        viewModel.getMoviesFav().observe(viewLifecycleOwner, {movieFav ->
            loading(false)
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


    private fun loading(load: Boolean) {
        when (load) {
            true -> fragmentMovieFavBinding.progresBar.visibility = View.VISIBLE
            false -> fragmentMovieFavBinding.progresBar.visibility = View.GONE
        }
    }
}