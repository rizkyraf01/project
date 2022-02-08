package com.example.jetpackpro2.fav.favtv

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
import com.example.jetpackpro2.databinding.FragmentFavTvBinding
import com.google.android.material.snackbar.Snackbar


class FavTvFragment : Fragment() {

    private lateinit var fragmentTvFavBinding: FragmentFavTvBinding
    private lateinit var viewModel: FavTvViewModel
    private lateinit var tvFavAdapter: FavTvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentTvFavBinding = FragmentFavTvBinding.inflate(layoutInflater)
        return (fragmentTvFavBinding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(fragmentTvFavBinding.rvTvFav)

        val factory = FactoryView.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavTvViewModel::class.java]

        tvFavAdapter = FavTvAdapter()
        viewModel.getTvShowFav().observe(viewLifecycleOwner, {movieFav ->
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
}