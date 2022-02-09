package com.dicoding.jetpacksubmission3.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.dicoding.jetpacksubmission3.R
import com.dicoding.jetpacksubmission3.databinding.FragmentFavoritesBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavoritesFragment : Fragment() {


    private lateinit var fragmentFavoritesBinding: FragmentFavoritesBinding
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.movies,
            R.string.tv_shows
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentFavoritesBinding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return fragmentFavoritesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionPagerAdapter = ViewPagerAdapter(this)
        fragmentFavoritesBinding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(fragmentFavoritesBinding.tabLayout, fragmentFavoritesBinding.viewPager){tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }

}