package com.cg.multimusic.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.cg.libmultimusic.Song
import com.cg.multimusic.interfaces.MusicPlayer
import com.cg.multimusic.utils.config.ConfigTool

class MusicService() : Service(), MusicPlayer {

    override fun playMusic(song: Song) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(song.url)
        mediaPlayer.prepare()
        mediaPlayer.start()
        ConfigTool.addSongToPlayList(song)
    }

    override fun playMusic(index: Int) {
        //相对于播放列表的索引值
        val playList = ConfigTool.currentConfig.playLists["PlayList"]
        if (playList == null || playList.size == 0){
            return
        }
        else if (index < playList.size && index >= 0)
        {
            playMusic(playList[index])
        }
        else if (index >= playList.size){
            playMusic(playList[playList.size-1])
        }
        else if (index < 0){
            playMusic(playList[0])
        }
    }

    override fun stopMusic() {
        if(mediaPlayer.isPlaying)
            mediaPlayer.stop()
    }

    override fun pauseMusic() {
        if (mediaPlayer.isPlaying)
            mediaPlayer.pause()
    }

    override fun resumeMusic() {
        if(!mediaPlayer.isPlaying)
            mediaPlayer.start()
    }

    override fun nextMusic() {
        val playList = ConfigTool.currentConfig.playLists["PlayList"]
        if (playList == null || playList.size == 0){
            return
        }
        else {
            val index = if(++ ConfigTool.currentConfig.index >= playList.size) 0 else ConfigTool.currentConfig.index
            playMusic(playList[index])
        }
    }

    override fun previousMusic() {
        val playList = ConfigTool.currentConfig.playLists["PlayList"]
        if (playList == null || playList.size == 0){
            return
        }
        else {
            val index = if(-- ConfigTool.currentConfig.index < 0) playList.size - 1 else ConfigTool.currentConfig.index
            playMusic(playList[index])
        }
    }

    private val binder = MyServiceBinder()


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

