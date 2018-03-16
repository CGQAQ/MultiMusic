package com.cg.multimusic.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.cg.multimusic.R
import android.widget.ArrayAdapter.createFromResource
import com.cg.libmultimusic.MultiMusicAPI
import com.cg.libmultimusic.Result
import com.cg.multimusic.adapter.SearchResultAdapter

import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.experimental.launch


class SearchFragment : Fragment() {
    var service = MultiMusicAPI.ServiceType.NETEASE


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
        spSearchFragmentServices.setOnItemClickListener { _, _, position, _ ->
                service = MultiMusicAPI.ServiceType.values()[position]
        }

        btnSearch.setOnClickListener {
            launch {
                val result = MultiMusicAPI.search(edtSearch.text.toString(), service)
                val adapter = SearchResultAdapter(getContext())
                result.data?.iterator()?.forEach {
                    adapter.addSong(it.title ?: "", it.author ?: "",  R.drawable.player_btn_play)
                }
                rvSearchResultList.adapter = adapter
            }
        }
    }
}
