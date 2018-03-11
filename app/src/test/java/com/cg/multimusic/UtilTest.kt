package com.cg.multimusic

import com.cg.multimusic.utils.MusicScanner
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilTest {
    @Test
    fun musicScanner(){
        MusicScanner.scan("/home/jason/Music")

        for (music in MusicScanner.musicPaths){
            print(music)
        }
    }
}
