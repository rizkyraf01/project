package com.example.jetpackpro2.film

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jetpackpro2.BuildConfig
import com.example.jetpackpro2.FactoryView
import com.example.jetpackpro2.R
import com.example.jetpackpro2.adapter.FilmAdapter
import com.example.jetpackpro2.databinding.ActivityFilmDetailBinding
import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.utils.SortUtils.RANDOMIN
import com.example.jetpackpro2.vo.Status

class FilmDetail : AppCompatActivity(), View.OnClickListener {
    private lateinit var bindingFilm: ActivityFilmDetailBinding
    private lateinit var viewModel: DataFilmDetailView

    companion object {
        const val DETAIL_FILM = "detail_film"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingFilm = ActivityFilmDetailBinding.inflate(layoutInflater)
        setContentView(bindingFilm.root)
        val factory = FactoryView.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DataFilmDetailView::class.java]
        bindingFilm.btnBack.setNavigationOnClickListener{onBackPressed()}
        bindingFilm.favButton.setOnClickListener(this)
        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(DETAIL_FILM)
                viewModel.selectFilm(movieId)
                setupFilmFav()

                viewModel.getMovie().observe(this, {movies ->
                    when(movies.status){
                        Status.SUCCESS -> {
                            movies.data?.let { checkFilm(it) }
                        }
                        Status.ERROR -> {
                            Toast.makeText(applicationContext, "Something Wrong..", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        rvOtherMovies()
    }

    private fun rvOtherMovies(){
        val moviesAdapter = FilmAdapter()
        viewModel.getMovies(RANDOMIN).observe(this, { movies ->
            when(movies.status){
                Status.SUCCESS -> {
                    moviesAdapter.submitList(movies.data)
                    moviesAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    Toast.makeText(this, "Something Wrong..", Toast.LENGTH_SHORT).show()
                }
            }
        })

        with(bindingFilm.rvAnotherMovie){
            layoutManager = LinearLayoutManager(this@FilmDetail, LinearLayoutManager.HORIZONTAL, false)
            adapter = moviesAdapter
        }
    }


    private fun checkFilm(movieEntity: DataFilmEntity) {
        with(bindingFilm){
            title.text = movieEntity.title
            filmRate.text = movieEntity.rate.toString()
            overview.text = movieEntity.overview

        }
        Glide.with(this)
            .load(BuildConfig.IMG_URL+movieEntity.banner)
            .apply(RequestOptions().override(3500, 2000))
            .centerCrop()
            .into(bindingFilm.itemImgPhoto)
    }
    private fun setupFilmFav(){
        viewModel.getMovie().observe(this, {movies ->
            when(movies.status){
                Status.SUCCESS -> {
                    if (movies.data !=null){
                        val fav = movies.data.favorite
                        setFavorited(fav)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(applicationContext, "Something Wrong..", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.fav_button -> {
                    viewModel.setFavoriteMovie()
                }
            }
        }
    }

    private fun setFavorited(fav: Boolean){
        when(fav){
            true -> bindingFilm.favButton
                .setImageResource(R.drawable.ic_baseline_favorite)
            false -> bindingFilm.favButton
                .setImageResource(R.drawable.ic_baseline_favorite_border)
        }
    }
}