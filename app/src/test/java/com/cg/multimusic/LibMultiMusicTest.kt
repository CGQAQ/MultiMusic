package com.cg.multimusic

import com.cg.libmultimusic.MultiMusicAPI
import com.cg.multimusic.services.MusicService
import org.junit.Test
import com.github.kittinunf.fuel.*
import com.google.gson.*


class LibMultiMusicTest {

    @Test
    fun ifworks() {
//        print(MultiMusicAPI.printak())
    }

    @Test
    fun search() {
        val result = MultiMusicAPI.search("classic", MultiMusicAPI.ServiceType.QQ)
        print(result?.data?.get(0)?.url?:"")
    }

    @Test
    fun testFuelReliable(){

    }

}