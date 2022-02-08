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
import com.example.jetpackpro2.adapter.TvShowAdapter
import com.example.jetpackpro2.databinding.FragmentTvShowBinding
import com.example.jetpackpro2.tvshow.TvShowViewModel
import com.example.jetpackpro2.utils.SortUtils.RANDOM
import com.example.jetpackpro2.utils.SortUtils.RANDOMIN
import com.example.jetpackpro2.vo.Status

class TvShowFragment : Fragment() {
    private lateinit var bindingTv: FragmentTvShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingTv = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return bindingTv.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){

            val factory = FactoryView.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            val tvAdapter = TvShowAdapter()
            viewModel.getTvShow(RANDOM).observe(viewLifecycleOwner,{ tvShow ->
                when(tvShow.status){
                    Status.SUCCESS -> {
                        tvAdapter.submitList(tvShow.data)
                        tvAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Something Wrong..", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            with(bindingTv.recycleViewTv){
                layoutManager = GridLayoutManager(context,3)

                this.adapter = tvAdapter
            }
        }
    }
}