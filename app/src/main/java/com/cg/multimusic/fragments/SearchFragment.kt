package com.cg.multimusic.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

import com.cg.multimusic.R
import android.widget.ArrayAdapter.createFromResource
import android.widget.LinearLayout
import com.cg.libmultimusic.MultiMusicAPI
import com.cg.libmultimusic.Result
import com.cg.libmultimusic.Song
import com.cg.multimusic.adapter.SearchResultAdapter

import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.experimental.launch


class SearchFragment : Fragment() {
    private var service = MultiMusicAPI.ServiceType.NETEASE
    private val handler = MyHandler()
    private var searchResultAdapter: SearchResultAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter.createFromResource(context,
                R.array.services_spinner, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spSearchFragmentServices.adapter = adapter


        init()
    }

    private fun init() {


        spSearchFragmentServices.onItemSelectedListener =  object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                service = MultiMusicAPI.ServiceType.values()[position]
            }
        }

        btnSearch.setOnClickListener {
            launch {
                handler.sendEmptyMessage(MsgType.TYPE_START_SEARCH.ordinal)
                val result = MultiMusicAPI.search(edtSearch.text.toString(), service)
                searchResultAdapter = SearchResultAdapter(getContext())

                val msg = Message()
                msg.what = MsgType.TYPE_UPDATE_SEARCH_RESULT.ordinal
                msg.obj = result.data
                handler.sendMessage(msg)
            }
        }
    }

    enum class MsgType{
        TYPE_UPDATE_SEARCH_RESULT,
        TYPE_START_SEARCH
    }

    internal inner class MyHandler: Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what){
                MsgType.TYPE_UPDATE_SEARCH_RESULT.ordinal -> {
                    (msg.obj as List<Song>?)?.iterator()?.forEach {
                        searchResultAdapter?.addSong(it.title ?: "", it.author ?: "",  R.drawable.player_btn_play)
                    }
                    rvSearchResultList.adapter = searchResultAdapter
                    rvSearchResultList.layoutManager = LinearLayoutManager(activity)
                    btnSearch.isEnabled = true
                }
                MsgType.TYPE_START_SEARCH.ordinal -> {
                    btnSearch.isEnabled = false
                }
                else -> super.handleMessage(msg)
            }
        }
    }
}
