package com.dicoding.consumerapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.consumerapp.DetailUserActivity
import com.dicoding.consumerapp.R
import com.dicoding.consumerapp.data.UserData
import de.hdodenhof.circleimageview.CircleImageView

class UserListAdapter(private var user: ArrayList<UserData>) : RecyclerView.Adapter<UserListAdapter.ListViewHolder>() {

    fun setData(items: ArrayList<UserData>){
        user.clear()
        user.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        var userName : TextView = itemView.findViewById(R.id.userName)
        var realName : TextView = itemView.findViewById(R.id.name)
        var userImage : CircleImageView = itemView.findViewById(R.id.userImage)
        var company : TextView = itemView.findViewById(R.id.company)
        var location : TextView = itemView.findViewById(R.id.location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_user, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = user[position]
        Glide.with(holder.itemView.context)
                .load(data.userImage)
                .apply(RequestOptions().override(250,250))
                .into(holder.userImage)
        holder.userName.text = data.username
        holder.realName.text = data.name
        holder.company.text = data.company
        holder.location.text = data.location
    }

    override fun getItemCount(): Int = user.size
}