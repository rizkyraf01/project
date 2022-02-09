package com.dicoding.submisionfundamental3

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.dicoding.submisionfundamental3.adapter.ViewPagerAdapter
import com.dicoding.submisionfundamental3.data.FavoriteUserData
import com.dicoding.submisionfundamental3.data.UserData
import com.dicoding.submisionfundamental3.databinding.ActivityDetailUserBinding
import com.dicoding.submisionfundamental3.db.DatabaseContract.FavoriteColumns.Companion.COMPANY
import com.dicoding.submisionfundamental3.db.DatabaseContract.FavoriteColumns.Companion.CONTENT_URI
import com.dicoding.submisionfundamental3.db.DatabaseContract.FavoriteColumns.Companion.FAVORITE
import com.dicoding.submisionfundamental3.db.DatabaseContract.FavoriteColumns.Companion.FOLLOWERS
import com.dicoding.submisionfundamental3.db.DatabaseContract.FavoriteColumns.Companion.FOLLOWING
import com.dicoding.submisionfundamental3.db.DatabaseContract.FavoriteColumns.Companion.LOCATION
import com.dicoding.submisionfundamental3.db.DatabaseContract.FavoriteColumns.Companion.NAME
import com.dicoding.submisionfundamental3.db.DatabaseContract.FavoriteColumns.Companion.REPOSITORY
import com.dicoding.submisionfundamental3.db.DatabaseContract.FavoriteColumns.Companion.USERIMAGE
import com.dicoding.submisionfundamental3.db.DatabaseContract.FavoriteColumns.Companion.USERNAME
import com.dicoding.submisionfundamental3.db.FavoriteHelper
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailUserBinding

    private var statusFavorite = false
    private lateinit var favHelper: FavoriteHelper
    private var favorites: FavoriteUserData? = null
    private lateinit var imageAvatar: String

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
                R.string.following,
                R.string.follower,
                R.string.repositories
        )
        const val EXTRA_USER = "extra_user"
        const val EXTRA_FAV = "extra_fav"
        const val EXTRA_DATA = "extra__data"
        const val EXTRA_POSITION = "extra_position"
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

        favHelper = FavoriteHelper.getInstance(applicationContext)
        favHelper.open()

        favorites = intent.getParcelableExtra(EXTRA_DATA)

        if (favorites != null) {
            setDataObject()
            statusFavorite = true
            val checked: Int = R.drawable.ic_favorite
            binding.favButton.setImageResource(checked)
        } else {
            showDetailUser()
        }

        binding.favButton.setOnClickListener(this)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun showDetailUser(){
        val dataUser = intent.getParcelableExtra<UserData>(EXTRA_USER) as UserData
        dataUser.name?.let{
            if (supportActionBar != null){
                supportActionBar?.title = dataUser.name
            }
        }
        binding.apply {
            name.text = dataUser.name
            userName.text =  dataUser.username
            company.text =  dataUser.company
            location.text =  dataUser.location
            following.text =  dataUser.following
            follower.text =  dataUser.followers
            repository.text =  dataUser.repository
        }
        Glide.with(this)
                .load(dataUser.userImage)
                .into(binding.userImage)
        imageAvatar = dataUser.userImage.toString()
    }

    private fun setDataObject() {
        val favoriteUser = intent.getParcelableExtra<FavoriteUserData>(EXTRA_DATA) as FavoriteUserData
        favoriteUser.name?.let {
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

    override fun onClick(v: View) {
        val checked: Int = R.drawable.ic_fav_on
        val unChecked: Int = R.drawable.ic_baseline_star_border_24
        if (v.id == R.id.favButton) {
            if (statusFavorite) {
                favHelper.delete(favorites?.username.toString())
                Toast.makeText(this, getString(R.string.delete_favorite), Toast.LENGTH_SHORT).show()
                binding.favButton.setImageResource(unChecked)
                statusFavorite = false
            } else {
                val userName = binding.userName.text.toString()
                val realName = binding.name.text.toString()
                val userImage = imageAvatar
                val company = binding.company.text.toString()
                val location = binding.location.text.toString()
                val following = binding.following.text.toString()
                val followers = binding.follower.text.toString()
                val repository = binding.repository.text.toString()
                val favorite = "1"

                val values = ContentValues()
                values.put(USERNAME, userName)
                values.put(NAME, realName)
                values.put(USERIMAGE, userImage)
                values.put(COMPANY, company)
                values.put(LOCATION, location)
                values.put(FOLLOWING, following)
                values.put(FOLLOWERS, followers)
                values.put(REPOSITORY, repository)
                values.put(FAVORITE, favorite)

                statusFavorite = true
                contentResolver.insert(CONTENT_URI, values)
                Toast.makeText(this, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
                binding.favButton.setImageResource(checked)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        favHelper.close()
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