package com.dicoding.jetpacksubmission3.ui.favorites.tvshowfav

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
import com.dicoding.jetpacksubmission3.databinding.FragmentTvFavBinding
import com.dicoding.jetpacksubmission3.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class TvFavFragment : Fragment() {

    private lateinit var fragmentTvFavBinding: FragmentTvFavBinding
    private lateinit var viewModel: TvFavViewModel
    private lateinit var tvFavAdapter: TvFavAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentTvFavBinding = FragmentTvFavBinding.inflate(layoutInflater)
        return (fragmentTvFavBinding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(fragmentTvFavBinding.rvTvFav)

        loading(true)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[TvFavViewModel::class.java]

        tvFavAdapter = TvFavAdapter()
        viewModel.getTvShowFav().observe(viewLifecycleOwner, {movieFav ->
            loading(false)
            tvFavAdapter.submitList(movieFav)
        })

        with(fragmentTvFavBinding.rvTvFav){
            layoutManager = LinearLayoutManager(context)
            adapter = tvFavAdapter
        }

    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val courseEntity = tvFavAdapter.getSwipedData(swipedPosition)
                courseEntity?.let { viewModel.setTvShowFav(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    courseEntity?.let { viewModel.setTvShowFav(it) }
                }
                snackbar.show()
            }
        }
    })

    private fun loading(load: Boolean) {
        when (load) {
            true -> fragmentTvFavBinding.progresBar.visibility = View.VISIBLE
            false -> fragmentTvFavBinding.progresBar.visibility = View.GONE
        }
    }
}