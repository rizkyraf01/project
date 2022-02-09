package com.dicoding.jetpacksubmission3.ui.tvshows

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
import com.dicoding.jetpacksubmission3.databinding.ListMovieBinding
import com.dicoding.jetpacksubmission3.ui.detail.DetailTvActivity

class TvShowsAdapter: PagedListAdapter<TvShowEntity, TvShowsAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

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

    class TvShowViewHolder(private val binding: ListMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity){
            with(binding){
                filmTitle.text = tvShow.title
                filmRate.text = tvShow.rate.toString()
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_TV, tvShow.tvId)
                    itemView.context.startActivities(arrayOf(intent))
                }
                Glide.with(itemView.context)
                    .load(BuildConfig.IMG_URL+tvShow.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading_poster)
                        .error(R.drawable.ic_loading_poster))
                    .into(poster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val listMovieBinding = ListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(listMovieBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

}