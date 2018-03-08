package com.cg.multimusic.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.cg.multimusic.R
import kotlinx.android.synthetic.main.play_list_item.view.*

/**
 * Created by mjaso on 2018/3/7.
 */
class RecyclerViewAdapter(val context: Context): RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private val pics = ArrayList<Int>()
    private val titles = ArrayList<String>()

    override fun onBindViewHolder(customViewHolder: CustomViewHolder, position: Int) {
        customViewHolder.pic.setImageResource(pics[position])
        customViewHolder.title.text = titles[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder{
        val view= inflater .inflate(R.layout.play_list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pics.size
    }

    fun addItem(pic: Int, title:String){
        pics.add(pic)
        titles.add(title)
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.playListItemTitle)
        var pic: ImageView = itemView.findViewById(R.id.playListItemPic)
    }
}