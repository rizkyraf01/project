package com.dicoding.jetpacksubmission3.ui.favorites.moviefav

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
import com.dicoding.jetpacksubmission3.databinding.ListFavoriteBinding
import com.dicoding.jetpacksubmission3.ui.detail.DetailMoviesActivity

class MovieFavAdapter : PagedListAdapter<MoviesEntity, MovieFavAdapter.MovieFavViewHolder>(DIFF_CALLBACK) {

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

    fun getSwipedData(swipedPosition: Int): MoviesEntity? = getItem(swipedPosition)

    class MovieFavViewHolder(private val binding: ListFavoriteBinding) :
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
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading_poster)
                        .error(R.drawable.ic_loading_poster))
                    .into(banner)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavViewHolder {
        val listMovieFavBinding =
            ListFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieFavViewHolder(listMovieFavBinding)
    }

    override fun onBindViewHolder(holder: MovieFavViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }
}