package com.dicoding.submisionfundamental3.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.submisionfundamental3.fragment.FollowersFragment
import com.dicoding.submisionfundamental3.fragment.FollowingFragment
import com.dicoding.submisionfundamental3.fragment.ReposFragment

class ViewPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity)  {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FollowingFragment()
            1 -> fragment = FollowersFragment()
            else -> fragment = ReposFragment()
        }
        return fragment as Fragment
    }
}