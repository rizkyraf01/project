package com.dicoding.submisionfundamental3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submisionfundamental3.adapter.UserListAdapter
import com.dicoding.submisionfundamental3.data.UserData
import com.dicoding.submisionfundamental3.databinding.ActivityMainBinding
import com.dicoding.submisionfundamental3.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : UserListAdapter
    private var user : ArrayList<UserData> = ArrayList()
    private lateinit var mainViewModel: MainViewModel

    companion object{
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserListAdapter(user)
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(MainViewModel::class.java)

        supportActionBar?.title = resources.getString(R.string.title)

        binding.rvUserList.layoutManager = LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
        binding.rvUserList.adapter = adapter

        showListUser()
        viewModelConfig()
        searchEngineUser()

    }

    private fun showListUser(){
        mainViewModel.getListUser(applicationContext)
        loading(true)
    }

    private fun viewModelConfig(){
        mainViewModel.getUser().observe(this, {listUser->
            if (listUser != null){
                adapter.setData(listUser)
                loading(false)
            }
        })
    }


    private fun searchEngineUser(){
        binding.search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()){
                    return true
                }else{
                    user.clear()
                    mainViewModel.searchUser(query, applicationContext)
                    loading(true)
                }
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun loading(load: Boolean){
        when(load){
            true -> binding.progresBar.visibility = View.VISIBLE
            false -> binding.progresBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorite ->{
                val mIntent = Intent(this, FavoriteUserActivity::class.java)
                startActivity(mIntent)
            }
            R.id.settings ->{
                val mIntent = Intent(this, SettingActivity::class.java)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}