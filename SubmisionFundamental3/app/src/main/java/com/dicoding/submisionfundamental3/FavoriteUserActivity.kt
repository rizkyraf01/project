package com.dicoding.submisionfundamental3

import android.database.ContentObserver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submisionfundamental3.adapter.FavoriteListAdapter
import com.dicoding.submisionfundamental3.data.FavoriteUserData
import com.dicoding.submisionfundamental3.databinding.ActivityFavoriteUserBinding
import com.dicoding.submisionfundamental3.db.DatabaseContract.FavoriteColumns.Companion.CONTENT_URI
import com.dicoding.submisionfundamental3.db.FavoriteHelper
import com.dicoding.submisionfundamental3.helper.MappingHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteUserBinding
    private lateinit var adapter: FavoriteListAdapter

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.fav_user)

        binding.rvFavoriteList.layoutManager = LinearLayoutManager(this)
        adapter = FavoriteListAdapter(this)
        binding.rvFavoriteList.adapter = adapter

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        val myObserver = object : ContentObserver(handler) {
            override fun onChange(self: Boolean) {
                loadFavoriteAsync()
            }
        }

        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)

        if (savedInstanceState == null) {
            loadFavoriteAsync()
        } else {
            val list = savedInstanceState.getParcelableArrayList<FavoriteUserData>(EXTRA_STATE)
            if (list != null) {
                adapter.listFavorite = list
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.listFavorite)
    }

    private fun loadFavoriteAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            loading(true)
            val defferedFav = async(Dispatchers.IO){
                val cursor = contentResolver.query(CONTENT_URI, null, null, null, null)
                MappingHelper.mapCursorToArrayList(cursor)
            }
            loading(false)
            val favs = defferedFav.await()
            if(favs.size > 0){
                adapter.listFavorite = favs
            }else{
                adapter.listFavorite = ArrayList()
                showEmptyMessage(R.string.no_data)
            }
        }
    }
    private fun showEmptyMessage(message: Int) {
        Snackbar.make(binding.rvFavoriteList, message, Snackbar.LENGTH_SHORT).show()
    }


    private fun loading(load: Boolean){
        when(load){
            true -> binding.progresBar.visibility = View.VISIBLE
            false -> binding.progresBar.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        loadFavoriteAsync()
    }

}