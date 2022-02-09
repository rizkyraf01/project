package com.dicoding.submisionfundamental3.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.submisionfundamental3.DetailUserActivity
import com.dicoding.submisionfundamental3.R
import com.dicoding.submisionfundamental3.custom.CustomOnItemClickListener
import com.dicoding.submisionfundamental3.data.FavoriteUserData
import com.dicoding.submisionfundamental3.data.UserData
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.list_user.view.*

class FavoriteListAdapter(private val activity: Activity) : RecyclerView.Adapter<FavoriteListAdapter.FavoriteViewHolder>(){

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
                                intent.putExtra(DetailUserActivity.EXTRA_POSITION, position)
                                intent.putExtra(DetailUserActivity.EXTRA_DATA, fav)
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