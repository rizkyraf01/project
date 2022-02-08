package com.example.jetpackpro2

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackpro2.fav.favfilm.FavFilmViewModel
import com.example.jetpackpro2.fav.favtv.FavTvViewModel
import com.example.jetpackpro2.film.DataFilmDetailView
import com.example.jetpackpro2.film.FilmViewModel
import com.example.jetpackpro2.film.RepoFilm
import com.example.jetpackpro2.tvshow.DataTvDetailView
import com.example.jetpackpro2.tvshow.TvShowViewModel

class FactoryView private constructor(private val bindingrepo: RepoFilm): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instfactory: FactoryView? = null
        fun getInstance(fcontext: Context): FactoryView =
                instfactory ?: synchronized(this) {
                instfactory ?: FactoryView(Injection.provideRepository(fcontext)).apply { instfactory = this }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(FilmViewModel::class.java) -> {
                return FilmViewModel(bindingrepo) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(bindingrepo) as T
            }
            modelClass.isAssignableFrom(DataFilmDetailView::class.java) -> {
                return DataFilmDetailView(bindingrepo) as T
            }
            modelClass.isAssignableFrom(DataTvDetailView::class.java) -> {
                return DataTvDetailView(bindingrepo) as T
            }
            modelClass.isAssignableFrom(FavFilmViewModel::class.java) -> {
                return FavFilmViewModel(bindingrepo) as T
            }
            modelClass.isAssignableFrom(FavTvViewModel::class.java) -> {
                return FavTvViewModel(bindingrepo) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}