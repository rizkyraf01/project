package com.dicoding.submisionfundamental3.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submisionfundamental3.DetailUserActivity
import com.dicoding.submisionfundamental3.DetailUserActivity.Companion.EXTRA_DATA
import com.dicoding.submisionfundamental3.FavoriteUserActivity
import com.dicoding.submisionfundamental3.R
import com.dicoding.submisionfundamental3.adapter.UserListAdapter
import com.dicoding.submisionfundamental3.data.FavoriteUserData
import com.dicoding.submisionfundamental3.data.UserData
import com.dicoding.submisionfundamental3.databinding.FragmentFollowingBinding
import com.dicoding.submisionfundamental3.viewModel.FollowingViewModel
import kotlinx.android.synthetic.main.activity_detail_user.*


class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private var user = arrayListOf<UserData>()

    private lateinit var adapter: UserListAdapter
    private lateinit var followingViewModel: FollowingViewModel

    private var favorites: FavoriteUserData? = null
    private lateinit var dataFavorite: FavoriteUserData

    companion object{
        val TAG = FollowingFragment::class.java.simpleName
        const val EXTRA_USER = "extra_user"
        const val EXTRA_FAV = "extra_favorite"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFollowingBinding.bind(view)
        adapter = UserListAdapter(user)
        user.clear()

        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(FollowingViewModel::class.java)

        binding.rvFollowingList.layoutManager = LinearLayoutManager(activity)
        adapter = UserListAdapter(user)
        binding.rvFollowingList.adapter = adapter

        showListUserFollowing()
        viewModelConfig()
    }

    private fun showListUserFollowing(){
        favorites = activity!!.intent.getParcelableExtra(DetailUserActivity.EXTRA_DATA)
        if (favorites != null) {
            dataFavorite = activity?.intent?.getParcelableExtra<FavoriteUserData>(EXTRA_DATA) as FavoriteUserData
            followingViewModel.getUserListFollowing(dataFavorite.username.toString(), activity!!.applicationContext)
        } else {
            val dataUser = activity?.intent?.getParcelableExtra<UserData>(EXTRA_USER) as UserData
            followingViewModel.getUserListFollowing(dataUser.username.toString(), activity!!.applicationContext)
            loading(true)
        }
    }

    private fun viewModelConfig(){
        followingViewModel.getUser().observe(viewLifecycleOwner,{ listUser->
            if (listUser != null){
                adapter.setData((listUser))
                loading(false)
            }
        })
    }

    private fun loading(load: Boolean){
        when(load){
            true -> binding.progresBar.visibility = View.VISIBLE
            false -> binding.progresBar.visibility = View.GONE
        }
    }


}