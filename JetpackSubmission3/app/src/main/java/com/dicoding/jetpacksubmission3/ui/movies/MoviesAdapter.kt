package com.dicoding.jetpacksubmission3.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jetpacksubmission3.BuildConfig
import com.dicoding.jetpacksubmission3.R
import com.dicoding.jetpacksubmission3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpacksubmission3.databinding.ListMovieBinding
import com.dicoding.jetpacksubmission3.ui.detail.DetailMoviesActivity

class MoviesAdapter: PagedListAdapter<MoviesEntity, MoviesAdapter.MoviesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }
            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MoviesViewHolder(private val binding: ListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(moviesDetail: MoviesEntity) {
            with(binding) {
                filmTitle.text = moviesDetail.title
                filmRate.text = moviesDetail.rate.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMoviesActivity::class.java)
                    intent.putExtra(DetailMoviesActivity.EXTRA_FILM, moviesDetail.movieId)
                    itemView.context.startActivities(arrayOf(intent))
                }
                Glide.with(itemView.context)
                    .load(BuildConfig.IMG_URL + moviesDetail.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading_poster)
                        .error(R.drawable.ic_loading_poster))
                    .into(poster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val listMovieBinding =
            ListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(listMovieBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }
}