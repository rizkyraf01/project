package com.example.jetpackpro2.fav.favfilm

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jetpackpro2.BuildConfig
import com.example.jetpackpro2.R
import com.example.jetpackpro2.databinding.ItemFavoriteBinding
import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.film.FilmDetail


class FavFilmAdapter : PagedListAdapter<DataFilmEntity, FavFilmAdapter.MovieFavViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataFilmEntity>() {
            override fun areItemsTheSame(oldItem: DataFilmEntity, newItem: DataFilmEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }
            override fun areContentsTheSame(oldItem: DataFilmEntity, newItem: DataFilmEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): DataFilmEntity? = getItem(swipedPosition)

    class MovieFavViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(moviesDetail: DataFilmEntity) {
            with(binding) {
                filmTitle.text = moviesDetail.title
                filmRate.text = moviesDetail.rate.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, FilmDetail::class.java)
                    intent.putExtra(FilmDetail.DETAIL_FILM, moviesDetail.movieId)
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
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieFavViewHolder(listMovieFavBinding)
    }

    override fun onBindViewHolder(holder: MovieFavViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }
}