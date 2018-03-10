package com.cg.multimusic.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cg.multimusic.R
import com.cg.multimusic.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_play_list.*


/**
 * A simple [Fragment] subclass.
 */
class PlayListFragment: Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerViewAdapter = RecyclerViewAdapter(activity as Context)
        recyclerViewAdapter.addItem(R.color.primaryLightColor, "nihao")
        recyclerViewAdapter.addItem(R.color.primaryLightColor, "nihao")
        recyclerViewAdapter.addItem(R.color.primaryLightColor, "nihao")
        recyclerViewAdapter.addItem(R.color.primaryLightColor, "nihao")
        recyclerViewAdapter.addItem(R.color.primaryLightColor, "nihao")
        recyclerViewAdapter.addItem(R.color.primaryLightColor, "nihao")
        recyclerViewAdapter.addItem(R.color.primaryLightColor, "nihao")
        mainPlayList.adapter = recyclerViewAdapter
        mainPlayList.layoutManager = LinearLayoutManager(activity)

        super.onViewCreated(view, savedInstanceState)
    }

}// Required empty public constructor
