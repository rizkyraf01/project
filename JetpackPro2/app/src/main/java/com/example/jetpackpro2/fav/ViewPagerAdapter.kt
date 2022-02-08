package com.example.jetpackpro2.fav

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jetpackpro2.fav.favfilm.FavFilmFragment
import com.example.jetpackpro2.fav.favtv.FavTvFragment

class ViewPagerAdapter  (fragment: Fragment): FragmentStateAdapter(fragment)  {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FavFilmFragment()
            1 -> fragment = FavTvFragment()
        }
        return fragment as Fragment
    }
}