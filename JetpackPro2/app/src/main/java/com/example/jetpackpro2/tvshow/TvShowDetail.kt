package com.example.jetpackpro2.tvshow

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
import com.example.jetpackpro2.adapter.TvShowAdapter
import com.example.jetpackpro2.databinding.ActivityTvShowDetailBinding
import com.example.jetpackpro2.datasource.DataTvEntity
import com.example.jetpackpro2.utils.SortUtils.RANDOMIN
import com.example.jetpackpro2.vo.Status

class TvShowDetail : AppCompatActivity(), View.OnClickListener {
    private lateinit var bindingTv: ActivityTvShowDetailBinding
    private lateinit  var viewModel : DataTvDetailView
    companion object {
        const val DETAIL_TV = "detail_tv"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTv = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(bindingTv.root)
        val factoryTv = FactoryView.getInstance(this)
        viewModel = ViewModelProvider(this, factoryTv)[DataTvDetailView::class.java]
        bindingTv.btnBack.setNavigationOnClickListener{onBackPressed()}
        bindingTv.favButton.setOnClickListener(this)
        val extrs = intent.extras
        if (extrs != null) {
            val tvId = extrs.getInt(DETAIL_TV)
            viewModel.setSelectTv(tvId)
            setupFilmFav()

            viewModel.getTvShow().observe(this, {tvShow ->
                when(tvShow.status){
                    Status.SUCCESS -> {
                        tvShow.data?.let { checkTv(it) }
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
        val moviesAdapter = TvShowAdapter()
        viewModel.getTvShow(RANDOMIN).observe(this, { movies ->
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

        with(bindingTv.rvAnotherMovie){
            layoutManager = LinearLayoutManager(this@TvShowDetail, LinearLayoutManager.HORIZONTAL, false)
            adapter = moviesAdapter
        }
    }

    private fun setupFilmFav(){
        viewModel.getTvShow().observe(this, {tvShow ->
            when(tvShow.status){
                Status.SUCCESS -> {
                    if (tvShow.data !=null){
                        val fav = tvShow.data.favorite
                        setFavorited(fav)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(applicationContext, "Something Wrong..", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun checkTv(movieEntity: DataTvEntity) {
        with(bindingTv){
            title.text = movieEntity.title
            filmRate.text = movieEntity.rate.toString()
            overview.text = movieEntity.overview
        }
        Glide.with(this)
            .load(BuildConfig.IMG_URL+movieEntity.banner)
            .apply(RequestOptions().override(3500, 2000))
            .into(bindingTv.itemImgPhoto)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.fav_button -> {
                    viewModel.setFavoriteTvShow()
                }
            }
        }
    }

    private fun setFavorited(fav: Boolean){
        when(fav){
            true -> bindingTv.favButton
                .setImageResource(R.drawable.ic_baseline_favorite)
            false -> bindingTv.favButton
                .setImageResource(R.drawable.ic_baseline_favorite_border)
        }
    }
}