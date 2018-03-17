package com.cg.multimusic.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cg.multimusic.R

/**
 * Created by jason on 3/16/18.
 */
class SearchResultAdapter(val context: Context): RecyclerView.Adapter<SearchResultAdapter.CustomViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    val songTitles: ArrayList<String> = ArrayList()
    val singers: ArrayList<String> = ArrayList()
    val pics: ArrayList<Int> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val view = inflater.inflate(R.layout.search_result_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.pic.setImageResource(pics[position])
        holder.songTitle.text = songTitles[position]
        holder.singer.text = singers[position]
    }

    override fun getItemCount(): Int {
        return  songTitles.size
    }

    fun addSong(songTitle: String, singer: String, pic: Int){
        songTitles.add(songTitle)
        singers.add(singer)
        pics.add(pic)
    }

    class CustomViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView){
        var songTitle: TextView = itemView.findViewById(R.id.tvSearchResultItemSongTitle) as TextView
        var singer: TextView = itemView.findViewById(R.id.tvSearchResultItemSinger) as TextView
        var pic: ImageView = itemView.findViewById(R.id.ivSearchResultItemSongPic) as ImageView
    }
}