package com.example.submissionjetpackpro1.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.submissionjetpackpro1.R
import com.example.submissionjetpackpro1.data.DataFilmEntity
import com.example.submissionjetpackpro1.databinding.ItemsFilmBinding

class FilmAdapter : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>(){

    private var listFilm = ArrayList<DataFilmEntity>()

    fun setCourses(courses: List<DataFilmEntity>?) {
        if (courses == null) return
        this.listFilm.clear()
        this.listFilm.addAll(courses)
    }
    class FilmViewHolder(private val binding: ItemsFilmBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data:DataFilmEntity){
        with(binding){
            tvItemTitle.text = data.title
            tvItemDate.text= itemView.resources.getString(R.string.deadline_date, data.director)
        }
    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}
