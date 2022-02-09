package com.dicoding.jetpacksubmission3.ui.favorites

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.jetpacksubmission3.ui.favorites.moviefav.MovieFavFragment
import com.dicoding.jetpacksubmission3.ui.favorites.tvshowfav.TvFavFragment

class ViewPagerAdapter (fragment: Fragment): FragmentStateAdapter(fragment)  {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = MovieFavFragment()
            1 -> fragment = TvFavFragment()

        }
        return fragment as Fragment
    }
}