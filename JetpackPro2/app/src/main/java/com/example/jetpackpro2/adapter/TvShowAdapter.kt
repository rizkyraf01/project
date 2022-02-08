package com.example.jetpackpro2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jetpackpro2.BuildConfig
import com.example.jetpackpro2.databinding.ItemRowDataBinding
import com.example.jetpackpro2.datasource.DataTvEntity
import com.example.jetpackpro2.tvshow.TvShowDetail

class TvShowAdapter: PagedListAdapter<DataTvEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {
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
    class TvShowViewHolder(private val binding: ItemRowDataBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: DataTvEntity){
            with(binding){
                titleFilm.text = tvShow.title
                rate.text = tvShow.rate.toString()
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, TvShowDetail::class.java)
                    intent.putExtra(TvShowDetail.DETAIL_TV, tvShow.tvId)
                    itemView.context.startActivities(arrayOf(intent))
                }
                Glide.with(itemView.context)
                    .load(BuildConfig.IMG_URL+tvShow.poster)
                    .apply(RequestOptions().override(350, 550))
                    .into(itemImgPhoto)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowAdapter.TvShowViewHolder {
        val listTvBinding = ItemRowDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(listTvBinding)
    }
    override fun onBindViewHolder(holder: TvShowAdapter.TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }
}