package com.dicoding.submisionfundamental3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submisionfundamental3.R
import com.dicoding.submisionfundamental3.data.UserData

class ReposListAdapter(private var user: ArrayList<UserData>) : RecyclerView.Adapter<ReposListAdapter.ListViewHolder>() {

    fun setData(items: ArrayList<UserData>){
        user.clear()
        user.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var repoName : TextView = itemView.findViewById(R.id.repoName)
        var repoDesc : TextView = itemView.findViewById(R.id.repoDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_repo, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = user[position]

        holder.repoName.text = data.repoName
        holder.repoDesc.text = data.repoDesc
        
    }

    override fun getItemCount(): Int = user.size
}