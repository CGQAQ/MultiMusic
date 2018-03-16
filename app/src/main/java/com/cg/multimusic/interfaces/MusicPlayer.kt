package com.cg.multimusic.interfaces

import com.cg.libmultimusic.Song

interface MusicPlayer{
    fun playMusic(song: Song)
    fun playMusic(index: Int)
    fun stopMusic()
    fun pauseMusic()
    fun resumeMusic()
    fun nextMusic()
    fun previousMusic()
}