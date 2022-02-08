package com.example.jetpackpro2.fav.favtv

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
import com.example.jetpackpro2.datasource.DataTvEntity
import com.example.jetpackpro2.tvshow.TvShowDetail

class FavTvAdapter : PagedListAdapter<DataTvEntity, FavTvAdapter.TvFavViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataTvEntity>() {
            override fun areItemsTheSame(oldItem: DataTvEntity, newItem: DataTvEntity): Boolean {
                return oldItem.tvId == newItem.tvId
            }
            override fun areContentsTheSame(oldItem: DataTvEntity, newItem: DataTvEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): DataTvEntity? = getItem(swipedPosition)

    class TvFavViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(moviesDetail: DataTvEntity) {
            with(binding) {
                filmTitle.text = moviesDetail.title
                filmRate.text = moviesDetail.rate.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, TvShowDetail::class.java)
                    intent.putExtra(TvShowDetail.DETAIL_TV, moviesDetail.tvId)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTvAdapter.TvFavViewHolder {
        val listMovieFavBinding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvFavViewHolder(listMovieFavBinding)
    }

    override fun onBindViewHolder(holder: FavTvAdapter.TvFavViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }
}