package com.cg.multimusic.utils

import java.io.File

object MusicScanner {
    val musicPaths: ArrayList<String> = ArrayList()
    val reg = Regex("\\.(mp3|ogg|wav)$")



    public fun scan(path: String) {
        val path = File(path)
        if(path.exists() && path.isDirectory) {
            val files: Array<out File> = path.listFiles()
            if (files.isNotEmpty()) {
                files.iterator().forEach {
                    if (it.isFile and reg.containsMatchIn(it.absolutePath)){
                        musicPaths.add(it.absolutePath)
                    }
                    else if(it.isDirectory){
                        scan(it.absolutePath)
                    }
                }
            }
        }
    }
}