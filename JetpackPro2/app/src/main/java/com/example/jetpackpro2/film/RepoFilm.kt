package com.example.jetpackpro2.film

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.jetpackpro2.vo.Resource
import com.example.jetpackpro2.RemoteDataSource
import com.example.jetpackpro2.api.ApiResponse
import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.datasource.DataTvEntity
import com.example.jetpackpro2.room.LocalDataSource
import com.example.jetpackpro2.room.NetworkBoundResource
import com.example.jetpackpro2.tvshow.ResponseTv
import com.example.jetpackpro2.utils.AppExecutors

class RepoFilm  private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors): FilmDataSource{
    companion object{
        private var instcnsrepo: RepoFilm? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): RepoFilm =
            instcnsrepo ?: synchronized(this) {
                instcnsrepo ?: RepoFilm(remoteData, localData, appExecutors).apply {
                    instcnsrepo = this
                }
            }
    }
    override fun getMovies(sort: String): LiveData<Resource<PagedList<DataFilmEntity>>> {
        return object : NetworkBoundResource<PagedList<DataFilmEntity>, List<ResponseFilm>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<DataFilmEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllMovies(sort), config).build()
            }
            override fun shouldFetch(data: PagedList<DataFilmEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResponseFilm>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<ResponseFilm>) {
                val moviesList = ArrayList<DataFilmEntity>()
                for (response in data) {
                    val movies = DataFilmEntity(
                        movieId = response.id,
                        title = response.title,
                        rate = response.ratee,
                        overview = response.overview,
                        poster = response.poster,
                        banner = response.background,
                        favorite = false
                    )
                    moviesList.add(movies)
                }
                localDataSource.insertMovies(moviesList)
            }
        }.asLiveData()
    }


    override fun getDetailMovies(movieId: Int): LiveData<Resource<DataFilmEntity>> {
        return object : NetworkBoundResource<DataFilmEntity, ResponseFilm>(appExecutors) {
            override fun loadFromDB(): LiveData<DataFilmEntity> = localDataSource.getMoviesById(movieId)

            override fun shouldFetch(data: DataFilmEntity?): Boolean =
                data != null

            override fun createCall(): LiveData<ApiResponse<ResponseFilm>> =
                remoteDataSource.getDetailMovies(movieId)

            override fun saveCallResult(data: ResponseFilm) {
                val movie = DataFilmEntity(
                    movieId = data.id,
                    title = data.title,
                    rate = data.ratee,
                    overview = data.overview,
                    poster = data.poster,
                    banner = data.background,
                    favorite = false
                )
                localDataSource.updateMovie(movie, false)
            }
        }.asLiveData()
    }

    override fun getMoviesFavorite(): LiveData<PagedList<DataFilmEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getMoviesFav(), config).build()
    }

    override fun setMoviesFavorite(movie: DataFilmEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setMoviesFavorite(movie, state)}
    }

    override fun getTvShows(sort: String): LiveData<Resource<PagedList<DataTvEntity>>> {
        return object : NetworkBoundResource<PagedList<DataTvEntity>, List<ResponseTv>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<DataTvEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllTvShows(sort), config).build()
            }
            override fun shouldFetch(data: PagedList<DataTvEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResponseTv>>> =
                remoteDataSource.getTvShow()

            override fun saveCallResult(data: List<ResponseTv>) {
                val moviesList = ArrayList<DataTvEntity>()
                for (response in data) {
                    val movies = DataTvEntity(
                        tvId = response.id,
                        title = response.name,
                        rate = response.voteAverage,
                        overview = response.overview,
                        poster = response.posterPath,
                        banner = response.backdropPath,
                        favorite = false
                    )
                    moviesList.add(movies)
                }
                localDataSource.insertTvShow(moviesList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(tvId: Int): LiveData<Resource<DataTvEntity>> {
        return object : NetworkBoundResource<DataTvEntity, ResponseTv>(appExecutors) {
            override fun loadFromDB(): LiveData<DataTvEntity> = localDataSource.getTvShowById(tvId)

            override fun shouldFetch(data: DataTvEntity?): Boolean =
                data != null

            override fun createCall(): LiveData<ApiResponse<ResponseTv>> =
                remoteDataSource.getDetailTv(tvId)

            override fun saveCallResult(data: ResponseTv) {
                val movie = DataTvEntity(
                    tvId = data.id,
                    title = data.name,
                    rate = data.voteAverage,
                    overview = data.overview,
                    poster = data.posterPath,
                    banner = data.backdropPath,
                    favorite = false
                )
                localDataSource.updateTvShow(movie, false)
            }
        }.asLiveData()
    }
    override fun getTvShowFavorite(): LiveData<PagedList<DataTvEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getTvShowsFav(), config).build()
    }

    override fun setTvShowFavorite(movie: DataTvEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(movie, state)}

    }


}