package com.cg.multimusic.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder

class MusicService : Service() {
    val binder = MyServiceBinder()




    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        return START_STICKY
    }

    inner class MyServiceBinder: Binder() {
        fun getService(): MusicService {
            return this@MusicService
        }
    }

    companion object {
        val TAG = MusicService::class.java.simpleName


        val mediaPlayer: MediaPlayer = MediaPlayer()
    }
}

