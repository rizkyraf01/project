package com.example.submissionfundamental2

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_row_user.view.*

var followingDataFilterList = ArrayList<DataUser>()

class FollowingAdapter(listUser: ArrayList<DataUser>) : RecyclerView.Adapter<FollowingAdapter.ListViewHolder>() {
    init {
        followingDataFilterList = listUser
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvPhoto: CircleImageView = itemView.rvImgPhoto
        var tvUsername: TextView = itemView.rvUsername
        var tvName: TextView = itemView.rvName
        var tvCompany: TextView = itemView.rvCompany
        var tvLocation: TextView = itemView.rvLocation
    }



    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_user, viewGroup, false)
        val listholder = ListViewHolder(view)
        mcontext = viewGroup.context
        return listholder
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = followingDataFilterList[position]
        Glide.with(holder.itemView.context)
            .load(data.photo)
            .apply(RequestOptions().override(250, 250))
            .into(holder.tvPhoto)
        holder.tvUsername.text = data.username
        holder.tvName.text = data.name
        holder.tvCompany.text = data.company
        holder.tvLocation.text = data.location
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(UserData: DataUser)
    }

    override fun getItemCount(): Int = followingDataFilterList.size


}