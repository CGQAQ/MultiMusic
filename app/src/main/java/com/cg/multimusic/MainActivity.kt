package com.cg.multimusic

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.support.design.widget.Snackbar
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

import com.cg.multimusic.adapter.*
import com.cg.multimusic.fragments.PlayerFragment
import com.cg.multimusic.services.MusicService
import kotlinx.android.synthetic.main.fragment_play_list.*


class MainActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // start music service and bind it
        val musicServiceIntent = Intent(applicationContext, MusicService::class.java)
        startService(musicServiceIntent)
        bindService(musicServiceIntent, MusicServiceConnection(), Context.BIND_AUTO_CREATE)

        supportFragmentManager.beginTransaction().replace(R.id.content_main_player_container, PlayerFragment()).commit()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        init()
    }

    fun init() {
        main_nav.setNavigationItemSelectedListener {
            it.isChecked = true
            mainDrawer.closeDrawers()
            return@setNavigationItemSelectedListener true
        }

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addItem(com.cg.multimusic.fragments.PlayListFragment(), "Play List")
        mainViewPager.adapter = viewPagerAdapter
        mainTabLayout.setupWithViewPager(mainViewPager)
    }
}


class MusicServiceConnection: ServiceConnection {
     lateinit var musicService: MusicService;


    override fun onServiceDisconnected(name: ComponentName) {
        Log.d("onServiceDisconnected", name.shortClassName)
    }

    override fun onServiceConnected(name: ComponentName, service: IBinder) {
        Log.d("onServiceConnected", name.shortClassName)
        musicService = (service as MusicService.MyServiceBinder).getService()
    }

}
