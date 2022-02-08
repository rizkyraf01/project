package com.example.jetpackpro2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackpro2.api.ApiConfig
import com.example.jetpackpro2.api.ApiResponse
import com.example.jetpackpro2.film.ResponseFilm
import com.example.jetpackpro2.film.ResponseFilmList
import com.example.jetpackpro2.tvshow.ResponseTv
import com.example.jetpackpro2.tvshow.ResponseTvList
import com.example.jetpackpro2.utils.EspressoIdlingRecource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor() {
    companion object{
        @Volatile
        private var instncremote: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instncremote ?: synchronized(this) {
            instncremote ?: RemoteDataSource()
        }
    }
    fun getMovies(): LiveData<ApiResponse<List<ResponseFilm>>> {
        EspressoIdlingRecource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<ResponseFilm>>>()
        val client = ApiConfig.getApiService().getMovies(BuildConfig.API_KEY)

        client.enqueue(object : Callback<ResponseFilmList> {
            override fun onResponse(call: Call<ResponseFilmList>, response: Response<ResponseFilmList>) {
                resultMovies.value = ApiResponse.success(response.body()?.results as List<ResponseFilm>)
                EspressoIdlingRecource.decrement()
            }

            override fun onFailure(call: Call<ResponseFilmList>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovies onFailure : ${t.message}")
                EspressoIdlingRecource.decrement()
            }
        })

        return resultMovies
    }
    fun getDetailMovies( movieId: Int): LiveData<ApiResponse<ResponseFilm>>  {
        EspressoIdlingRecource.increment()
        val resultFilm = MutableLiveData<ApiResponse<ResponseFilm>>()
        val client = ApiConfig.getApiService().getMovieDetail(movieId, BuildConfig.API_KEY)
        client.enqueue(object : Callback<ResponseFilm> {
            override fun onResponse(call: Call<ResponseFilm>, response: Response<ResponseFilm>) {
                resultFilm.value = ApiResponse.success(response.body() as ResponseFilm)
                EspressoIdlingRecource.decrement()
            }

            override fun onFailure(call: Call<ResponseFilm>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailMovies onFailure : ${t.message}")
                EspressoIdlingRecource.decrement()
            }
        })
        return resultFilm
    }

    fun getTvShow() : LiveData<ApiResponse<List<ResponseTv>>> {
        EspressoIdlingRecource.increment()
        val resultTv = MutableLiveData<ApiResponse<List<ResponseTv>>>()
        val client = ApiConfig.getApiService().getTvShows(BuildConfig.API_KEY)
        client.enqueue(object : Callback<ResponseTvList> {
            override fun onResponse(call: Call<ResponseTvList>, response: Response<ResponseTvList>) {
                resultTv.value = ApiResponse.success(response.body()?.results as List<ResponseTv>)
                EspressoIdlingRecource.decrement()
            }

            override fun onFailure(call: Call<ResponseTvList>, t: Throwable) {
                Log.e("RemoteDataSource", "getTvShow onFailure : ${t.message}")
                EspressoIdlingRecource.decrement()
            }
        })
        return resultTv
    }



    fun getDetailTv( tvId: Int): LiveData<ApiResponse<ResponseTv>> {
        EspressoIdlingRecource.increment()
        val resultFilm = MutableLiveData<ApiResponse<ResponseTv>>()
        val client = ApiConfig.getApiService().getTvShowDetail(tvId, BuildConfig.API_KEY)
        client.enqueue(object : Callback<ResponseTv> {
            override fun onResponse(call: Call<ResponseTv>, response: Response<ResponseTv>) {
                resultFilm.value = ApiResponse.success(response.body() as ResponseTv)
                EspressoIdlingRecource.decrement()
            }

            override fun onFailure(call: Call<ResponseTv>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailTv onFailure : ${t.message}")
                EspressoIdlingRecource.decrement()
            }
        })
        return resultFilm
    }

}