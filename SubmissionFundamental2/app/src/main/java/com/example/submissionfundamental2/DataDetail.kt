package com.example.submissionfundamental2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_data_detail.*

class DataDetail : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_detail)
        setData()
        viewPageConfig()
    }

    private fun viewPageConfig(){
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f
    }


    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            this.title = title
        }
    }


    @SuppressLint("SetTextI18n", "StringFormatInvalid")
    private fun setData() {
        val dataUser = intent.getParcelableExtra<DataUser>(EXTRA_DATA) as DataUser
        dataUser.name?.let { setActionBarTitle(it) }
        name.text = dataUser.name
        username.text = dataUser.username
        company.text = getString(R.string.company, dataUser.company)
        location.text = getString(R.string.location, dataUser.location)
        repository.text = getString(R.string.repository, dataUser.repository)
        Glide.with(this)
            .load(dataUser.photo)
            .into(photo)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}