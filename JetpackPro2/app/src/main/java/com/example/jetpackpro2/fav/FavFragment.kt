package com.example.jetpackpro2.fav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.example.jetpackpro2.R
import com.example.jetpackpro2.databinding.FragmentFavBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavFragment : Fragment() {

    private lateinit var fragmentFavoritesBinding: FragmentFavBinding
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.movies,
            R.string.tv_shows
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentFavoritesBinding = FragmentFavBinding.inflate(layoutInflater, container, false)
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