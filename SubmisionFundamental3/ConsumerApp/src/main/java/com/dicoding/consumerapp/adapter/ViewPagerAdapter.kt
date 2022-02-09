package com.dicoding.consumerapp.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.consumerapp.fragment.FollowersFragment
import com.dicoding.consumerapp.fragment.FollowingFragment

class ViewPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity)  {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FollowingFragment()
            1 -> fragment = FollowersFragment()
        }
        return fragment as Fragment
    }
}