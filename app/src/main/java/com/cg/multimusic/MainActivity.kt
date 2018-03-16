package com.cg.multimusic

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.cg.libmultimusic.MultiMusicAPI
import com.cg.multimusic.adapter.ViewPagerAdapter
import com.cg.multimusic.fragments.PlayerFragment
import com.cg.multimusic.interfaces.MusicPlayer
import com.cg.multimusic.services.MusicService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fagment_player.*


class MainActivity : AppCompatActivity() {

    val musicServiceConnection: MusicServiceConnection = MusicServiceConnection()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // start music service and bind it
        startMusicService()

        supportFragmentManager.beginTransaction().replace(R.id.content_main_player_container, PlayerFragment()).commit()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        init()
    }

    private fun startMusicService() {
        val musicServiceIntent = Intent(applicationContext, MusicService::class.java)
        startService(musicServiceIntent)
        bindService(musicServiceIntent, musicServiceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun init() {
        main_nav.setNavigationItemSelectedListener {
            it.isChecked = true
            mainDrawer.closeDrawers()
            return@setNavigationItemSelectedListener true
        }

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addItem(com.cg.multimusic.fragments.PlayListFragment(), "Play List")
        viewPagerAdapter.addItem(com.cg.multimusic.fragments.SearchFragment(), "Search")
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
