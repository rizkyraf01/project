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
import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.film.FilmDetail

class FilmAdapter: PagedListAdapter<DataFilmEntity, FilmAdapter.FilmViewHolder>(DIFF_CALLBACK) {
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

    class FilmViewHolder(private val binding: ItemRowDataBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(filmm: DataFilmEntity){
            with(binding){
                titleFilm.text = filmm.title
                rate.text = filmm.rate.toString()
                itemView.setOnClickListener{
                    val intn = Intent(itemView.context, FilmDetail::class.java)
                    intn.putExtra(FilmDetail.DETAIL_FILM, filmm.movieId)
                    itemView.context.startActivities(arrayOf(intn))
                }
                Glide.with(itemView.context)
                    .load(BuildConfig.IMG_URL+filmm.poster)
                    .apply(RequestOptions().override(350, 550))
                    .into(itemImgPhoto)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val listFilmBinding = ItemRowDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(listFilmBinding)
    }
    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }
}