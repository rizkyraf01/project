package com.dicoding.consumerapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.consumerapp.DetailUserActivity
import com.dicoding.consumerapp.R
import com.dicoding.consumerapp.adapter.UserListAdapter
import com.dicoding.consumerapp.data.FavoriteUserData
import com.dicoding.consumerapp.data.UserData
import com.dicoding.consumerapp.databinding.FragmentFollowersBinding
import com.dicoding.consumerapp.viewModel.FollowersViewModel


class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private var user = arrayListOf<UserData>()

    private lateinit var adapter: UserListAdapter
    private lateinit var followersViewModel: FollowersViewModel

    private var favorites: FavoriteUserData? = null
    private lateinit var dataFavorite: FavoriteUserData

    companion object{
        val TAG = FollowersFragment::class.java.simpleName
        const val EXTRA_USER = "extra_user"
        const val EXTRA_FAV = "extra_data"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFollowersBinding.bind(view)
        adapter = UserListAdapter(user)
        user.clear()

        followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(FollowersViewModel::class.java)

        binding.rvFollowerList.layoutManager = LinearLayoutManager(activity)
        adapter = UserListAdapter(user)
        binding.rvFollowerList.adapter = adapter

        showListUserFollowers()
        viewModelConfig()
    }

    private fun showListUserFollowers(){
        favorites = activity!!.intent.getParcelableExtra(DetailUserActivity.EXTRA_FAV)
        if (favorites != null) {
            dataFavorite = activity?.intent?.getParcelableExtra<FavoriteUserData>(EXTRA_FAV) as FavoriteUserData
            followersViewModel.getUserListFollowers(dataFavorite.username.toString(), activity!!.applicationContext)
        } else {
            val dataUser = activity?.intent?.getParcelableExtra<UserData>(EXTRA_USER) as UserData
            followersViewModel.getUserListFollowers(
                dataUser.username.toString(),
                activity!!.applicationContext)
            loading(true)
        }
    }

    private fun viewModelConfig(){
        followersViewModel.getUser().observe(viewLifecycleOwner,{ listUser->
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