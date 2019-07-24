package com.ankkumar.albums.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ankkumar.albums.R
import com.ankkumar.albums.model.AlbumEntity
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumAdapter(var context: Context, private val albumList: ArrayList<AlbumEntity>) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_album, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(albumList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return albumList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(dashBoardModel: AlbumEntity) {
            itemView.heading.text = dashBoardModel.title
        }
    }

    fun addList(list: List<AlbumEntity>) {
        albumList.clear()
        albumList.addAll(list)
        notifyDataSetChanged()
    }

}