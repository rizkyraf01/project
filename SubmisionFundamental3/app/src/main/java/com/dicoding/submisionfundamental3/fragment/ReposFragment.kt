package com.dicoding.submisionfundamental3.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submisionfundamental3.DetailUserActivity
import com.dicoding.submisionfundamental3.R
import com.dicoding.submisionfundamental3.adapter.ReposListAdapter
import com.dicoding.submisionfundamental3.adapter.UserListAdapter
import com.dicoding.submisionfundamental3.data.FavoriteUserData
import com.dicoding.submisionfundamental3.data.UserData
import com.dicoding.submisionfundamental3.databinding.FragmentFollowersBinding
import com.dicoding.submisionfundamental3.databinding.FragmentReposBinding
import com.dicoding.submisionfundamental3.viewModel.FollowersViewModel
import com.dicoding.submisionfundamental3.viewModel.RepoViewModel


class ReposFragment : Fragment() {

    private var _binding: FragmentReposBinding? = null
    private val binding get() = _binding!!
    private var user = arrayListOf<UserData>()

    private lateinit var adapter: ReposListAdapter
    private lateinit var repoViewModel: RepoViewModel

    private var favorites: FavoriteUserData? = null
    private lateinit var dataFavorite: FavoriteUserData

    companion object{
        val TAG = ReposFragment::class.java.simpleName
        const val EXTRA_USER = "extra_user"
        const val EXTRA_FAV = "extra_favorite"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentReposBinding.bind(view)
        user.clear()

        repoViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(RepoViewModel::class.java)

        binding.rvReposList.layoutManager = LinearLayoutManager(activity)
        adapter = ReposListAdapter(user)
        binding.rvReposList.adapter = adapter

        showRepo()
        viewModelConfig()

    }

    private fun showRepo(){
        favorites = activity!!.intent.getParcelableExtra(DetailUserActivity.EXTRA_DATA)
        if (favorites != null) {
            dataFavorite = activity?.intent?.getParcelableExtra<FavoriteUserData>(DetailUserActivity.EXTRA_DATA) as FavoriteUserData
            repoViewModel.getUserRepo(dataFavorite.username.toString(), activity!!.applicationContext)
        } else {
            val dataUser = activity?.intent?.getParcelableExtra<UserData>(EXTRA_USER) as UserData
            repoViewModel.getUserRepo(
                    dataUser.username.toString(),
                    activity!!.applicationContext
            )
            loading(true)
        }
    }

    private fun viewModelConfig(){
        repoViewModel.getUser().observe(viewLifecycleOwner,{ listUser->
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