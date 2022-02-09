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
import com.dicoding.jetpacksubmission3.databinding.ListTrendingBinding
import com.dicoding.jetpacksubmission3.ui.detail.DetailTvActivity

class TvPopularAdapter: PagedListAdapter<TvShowEntity, TvPopularAdapter.TvPopularViewHolder>(DIFF_CALLBACK) {

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

    class TvPopularViewHolder(private val binding: ListTrendingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity){
            with(binding){
                bannerTitle.text = tvShow.title
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_TV, tvShow.tvId)
                    itemView.context.startActivities(arrayOf(intent))
                }
                Glide.with(itemView.context)
                    .load(BuildConfig.IMG_URL+tvShow.banner)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading_banner)
                        .error(R.drawable.ic_loading_banner))
                    .into(banner)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvPopularViewHolder {
        val listTrendingBinding = ListTrendingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvPopularViewHolder(listTrendingBinding)
    }

    override fun onBindViewHolder(holder: TvPopularViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }
}