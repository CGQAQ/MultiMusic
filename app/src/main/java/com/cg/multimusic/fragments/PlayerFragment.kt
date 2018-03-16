package com.cg.multimusic.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cg.libmultimusic.MultiMusicAPI
import com.cg.multimusic.MainActivity
import com.cg.multimusic.R
import kotlinx.android.synthetic.main.fagment_player.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PlayerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PlayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PlayerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fagment_player, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

        btnPlay.setOnClickListener {

        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }
}
