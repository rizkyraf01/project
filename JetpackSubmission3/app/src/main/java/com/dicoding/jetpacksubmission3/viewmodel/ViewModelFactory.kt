package com.dicoding.jetpacksubmission3.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.jetpacksubmission3.data.FilmRepository
import com.dicoding.jetpacksubmission3.di.Injection
import com.dicoding.jetpacksubmission3.ui.detail.DetailMoviesViewModel
import com.dicoding.jetpacksubmission3.ui.detail.DetailTvViewModel
import com.dicoding.jetpacksubmission3.ui.favorites.moviefav.MovieFavViewModel
import com.dicoding.jetpacksubmission3.ui.favorites.tvshowfav.TvFavViewModel
import com.dicoding.jetpacksubmission3.ui.movies.MoviesViewModel
import com.dicoding.jetpacksubmission3.ui.tvshows.TvShowsViewModel

class ViewModelFactory private constructor(private val mFilmRepository: FilmRepository): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                return MoviesViewModel(mFilmRepository) as T
            }
            modelClass.isAssignableFrom(TvShowsViewModel::class.java) -> {
                return TvShowsViewModel(mFilmRepository) as T
            }
            modelClass.isAssignableFrom(DetailMoviesViewModel::class.java) -> {
                return DetailMoviesViewModel(mFilmRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvViewModel::class.java) -> {
                return DetailTvViewModel(mFilmRepository) as T
            }
            modelClass.isAssignableFrom(MovieFavViewModel::class.java) -> {
                return MovieFavViewModel(mFilmRepository) as T
            }
            modelClass.isAssignableFrom(TvFavViewModel::class.java) -> {
                return TvFavViewModel(mFilmRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}