package com.dicoding.consumerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.dicoding.consumerapp.adapter.ViewPagerAdapter
import com.dicoding.consumerapp.data.FavoriteUserData
import com.dicoding.consumerapp.databinding.ActivityDetailUserBinding

import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var imageAvatar: String

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
                R.string.following,
                R.string.follower
        )

        const val EXTRA_FAV = "extra_data"
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionPagerAdapter = ViewPagerAdapter(this)

        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

        showDetailUser()

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun showDetailUser(){
        val favoriteUser = intent.getParcelableExtra<FavoriteUserData>(EXTRA_FAV) as FavoriteUserData
        favoriteUser.name?.let{
            if (supportActionBar != null){
                supportActionBar?.title = favoriteUser.name
            }
        }
        binding.apply {
            name.text = favoriteUser.name
            userName.text =  favoriteUser.username
            company.text =  favoriteUser.company
            location.text =  favoriteUser.location
            following.text =  favoriteUser.following
            follower.text =  favoriteUser.followers
            repository.text =  favoriteUser.repository
        }
        Glide.with(this)
                .load(favoriteUser.userImage)
                .into(binding.userImage)
        imageAvatar = favoriteUser.userImage.toString()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings ->{
                val mIntent = Intent(this, SettingActivity::class.java)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}