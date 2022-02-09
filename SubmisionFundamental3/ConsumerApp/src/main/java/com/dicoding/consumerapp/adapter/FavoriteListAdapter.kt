package com.dicoding.consumerapp.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.consumerapp.DetailUserActivity
import com.dicoding.consumerapp.R
import com.dicoding.consumerapp.custom.CustomOnItemClickListener
import com.dicoding.consumerapp.data.FavoriteUserData
import com.dicoding.consumerapp.data.UserData
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_detail_user.view.*
import kotlinx.android.synthetic.main.list_user.view.*
import kotlinx.android.synthetic.main.list_user.view.company
import kotlinx.android.synthetic.main.list_user.view.location
import kotlinx.android.synthetic.main.list_user.view.name
import kotlinx.android.synthetic.main.list_user.view.userImage
import kotlinx.android.synthetic.main.list_user.view.userName

class FavoriteListAdapter(private val activity: Activity) : RecyclerView.Adapter<FavoriteListAdapter.FavoriteViewHolder>(){
    private var user: ArrayList<UserData> = ArrayList()

    fun setData(items: ArrayList<UserData>){
        user.clear()
        user.addAll(items)
        notifyDataSetChanged()
    }

    var listFavorite = ArrayList<FavoriteUserData>()
        set(listFavorite){
            if (listFavorite.size > 0){
                this.listFavorite.clear()
            }
            this.listFavorite.addAll(listFavorite)

            notifyDataSetChanged()
        }

    inner class FavoriteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(fav: FavoriteUserData) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(fav.userImage)
                    .apply(RequestOptions().override(250, 250))
                    .into(itemView.userImage)
                userName.text = fav.username
                name.text = fav.name
                company.text = fav.company.toString()
                location.text = fav.location.toString()
                itemView.setOnClickListener(
                    CustomOnItemClickListener(
                        adapterPosition,
                        object : CustomOnItemClickListener.OnItemClickCallback {
                            override fun onItemClicked(view: View, position: Int) {
                                val intent = Intent(activity, DetailUserActivity::class.java)
                                intent.putExtra(DetailUserActivity.EXTRA_USER, position)
                                intent.putExtra(DetailUserActivity.EXTRA_FAV, fav)
                                activity.startActivity(intent)
                            }
                        }))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_user, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavorite[position])
    }

    override fun getItemCount(): Int = this.listFavorite.size


}