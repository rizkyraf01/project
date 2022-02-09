package com.dicoding.jetpacksubmission3.ui.favorites.tvshowfav

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
import com.dicoding.jetpacksubmission3.data.source.local.entity.TvShowEntity
import com.dicoding.jetpacksubmission3.databinding.ListFavoriteBinding
import com.dicoding.jetpacksubmission3.ui.detail.DetailTvActivity

class TvFavAdapter : PagedListAdapter<TvShowEntity, TvFavAdapter.TvFavViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvId == newItem.tvId
            }
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): TvShowEntity? = getItem(swipedPosition)

    class TvFavViewHolder(private val binding: ListFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(moviesDetail: TvShowEntity) {
            with(binding) {
                filmTitle.text = moviesDetail.title
                filmRate.text = moviesDetail.rate.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_TV, moviesDetail.tvId)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvFavAdapter.TvFavViewHolder {
        val listMovieFavBinding =
            ListFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvFavViewHolder(listMovieFavBinding)
    }

    override fun onBindViewHolder(holder: TvFavAdapter.TvFavViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }
}