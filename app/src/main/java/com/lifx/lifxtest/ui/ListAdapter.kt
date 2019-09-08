package com.lifx.lifxtest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lifx.lifxtest.R
import com.lifx.lifxtest.model.ListItem
import com.squareup.picasso.Picasso

class ListAdapter(): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var listData: List<ListItem>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false))
    }

    override fun getItemCount(): Int = listData?.size?: 0

    fun setData(list: List<ListItem>){
        listData = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listData!![position]
        holder.title.text = item.title
        Picasso.get()
            .load(item.imageUrl)
            .into(holder.image)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textView)
        val image: ImageView = itemView.findViewById(R.id.imageView)
    }

}