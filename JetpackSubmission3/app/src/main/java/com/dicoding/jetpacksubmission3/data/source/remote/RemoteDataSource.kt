package com.dicoding.jetpacksubmission3.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.jetpacksubmission3.BuildConfig
import com.dicoding.jetpacksubmission3.api.ApiConfig
import com.dicoding.jetpacksubmission3.data.source.remote.response.*
import com.dicoding.jetpacksubmission3.utils.EspressoIdlingRecource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor() {
    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource()
        }
    }

    fun getMovies(): LiveData<ApiResponse<List<MoviesResponse>>> {
        EspressoIdlingRecource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MoviesResponse>>>()
        val client = ApiConfig.getApiService().getMovies(BuildConfig.API_KEY)

        client.enqueue(object : Callback<MoviesListResponse> {
            override fun onResponse(call: Call<MoviesListResponse>, response: Response<MoviesListResponse>) {
                resultMovies.value = ApiResponse.success(response.body()?.results as List<MoviesResponse>)
                EspressoIdlingRecource.decrement()
            }

            override fun onFailure(call: Call<MoviesListResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovies onFailure : ${t.message}")
                EspressoIdlingRecource.decrement()
            }
        })

        return resultMovies
    }

    fun getDetailMovies(movieId: Int): LiveData<ApiResponse<MoviesResponse>> {
        EspressoIdlingRecource.increment()
        val resultFilm = MutableLiveData<ApiResponse<MoviesResponse>>()
        val client = ApiConfig.getApiService().getMovieDetail(movieId, BuildConfig.API_KEY)
        client.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                resultFilm.value = ApiResponse.success(response.body() as MoviesResponse)
                EspressoIdlingRecource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailMovies onFailure : ${t.message}")
                EspressoIdlingRecource.decrement()
            }
        })
        return resultFilm
    }

    fun getTvShow() : LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingRecource.increment()
        val resultTv = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        val client = ApiConfig.getApiService().getTvShows(BuildConfig.API_KEY)
        client.enqueue(object : Callback<TvShowListResponse> {
            override fun onResponse(call: Call<TvShowListResponse>, response: Response<TvShowListResponse>) {
                resultTv.value = ApiResponse.success(response.body()?.results as List<TvShowResponse>)
                EspressoIdlingRecource.decrement()
            }

            override fun onFailure(call: Call<TvShowListResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getTvShow onFailure : ${t.message}")
                EspressoIdlingRecource.decrement()
            }
        })
        return resultTv
    }

    fun getDetailTv(tvId: Int): LiveData<ApiResponse<TvShowResponse>> {
        EspressoIdlingRecource.increment()
        val resultFilm = MutableLiveData<ApiResponse<TvShowResponse>>()
        val client = ApiConfig.getApiService().getTvShowDetail(tvId, BuildConfig.API_KEY)
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                resultFilm.value = ApiResponse.success(response.body() as TvShowResponse)
                EspressoIdlingRecource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailTv onFailure : ${t.message}")
                EspressoIdlingRecource.decrement()
            }
        })
        return resultFilm
    }

}