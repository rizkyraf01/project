package com.example.submissionfundamental2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_row_user.view.*
import java.util.*
import kotlin.collections.ArrayList

var userdataFilterList = ArrayList<DataUser>()
lateinit var mcontext: Context

class ListUserAdapter(private var Datalist: ArrayList<DataUser>) : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>(), Filterable {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        var tvusername:TextView= itemView.rvUsername
        var tvname:TextView= itemView.rvName
        var tvcompany:TextView=itemView.rvCompany
        var tvlocation:TextView=itemView.rvLocation
        var tvimgphoto:CircleImageView = itemView.rvImgPhoto
    }

    init {
        userdataFilterList = Datalist
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_user, viewGroup, false)
        val listholder = ListViewHolder(view)
        mcontext = viewGroup.context
        return listholder
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val orang = userdataFilterList[position]
        holder.tvusername.text= orang.username
        holder.tvname.text=orang.name
        holder.tvcompany.text=orang.company
        holder.tvlocation.text=orang.location
        Glide.with(holder.itemView.context)
            .load(orang.photo)
            .apply(RequestOptions().override(250, 250))
            .into(holder.tvimgphoto)
        holder.itemView.setOnClickListener {
            val data = DataUser(
                orang.username,
                orang.name,
                orang.photo,
                orang.company,
                orang.location,
                orang.repository,
                orang.followers,
                orang.following
            )
            val intentDetail = Intent(mcontext, DataDetail::class.java)
            intentDetail.putExtra(DataDetail.EXTRA_DATA, data)
            mcontext.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = userdataFilterList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charSearch = constraint.toString()
                userdataFilterList = if (charSearch.isEmpty()) {
                Datalist
                } else {
                    val resultList = ArrayList<DataUser>()
                    for (row in userdataFilterList) {
                        if ((row.username.toString().toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT)))
                        ) {
                            resultList.add(
                                DataUser(
                                    row.username,
                                    row.name,
                                    row.photo,
                                    row.company,
                                    row.location,
                                    row.repository,
                                    row.followers,
                                    row.following
                                )
                            )
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = userdataFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                userdataFilterList = results.values as ArrayList<DataUser>
                notifyDataSetChanged()
            }
        }
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(dataUsers: DataUser)
    }

}
