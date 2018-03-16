package com.cg.multimusic.utils.config

import android.os.Environment
import com.cg.libmultimusic.Song
import com.cg.multimusic.entry.Settings
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

/**
 * Created by jason on 3/15/18.
 */
object ConfigTool {
    private val EXTERNEL_PATH = Environment.getExternalStorageDirectory().absolutePath

    val HOMEPATH: String = "$EXTERNEL_PATH/MultiMusic"
    var currentConfig: Config = getConfigFromFile()

    val settingsFilePath: String = "$HOMEPATH/Settings.config"
    val playListsFilePath: String = "$HOMEPATH/PlayLists.config"
    val indexFilePath: String = "$HOMEPATH/Index.config"

    val gson = Gson()

    val homePath: File = File(HOMEPATH)
    val playlistsFile: File = File(playListsFilePath)
    val settingsFile: File = File(settingsFilePath)
    val indexFile: File = File(indexFilePath)



    fun addSongToPlayList(song: Song){
        currentConfig.playLists["PlayList"]!!.add(song)
        currentConfig.index = ConfigTool.currentConfig.playLists["PlayList"]!!.size - 1
        writeConfigToFile()
    }

    private fun getConfigFromFile(): Config {
        if (!homePath.exists()) {
            homePath.mkdir()
            return Config()
        }
        if(!playlistsFile.exists()) playlistsFile.createNewFile()
        if(!settingsFile.exists()) settingsFile.createNewFile()
        if(!indexFile.exists()) settingsFile.createNewFile()

        val playlistsInputStream = playlistsFile.inputStream()
        val playListsJsonString =  playlistsInputStream.reader().readText()

        val type = object: TypeToken<Map<String, ArrayList<Song>>>(){}.type
        val playlists  = gson.fromJson<Map<String, ArrayList<Song>>>(playListsJsonString, type)


        val settingsInputStream = settingsFile.inputStream()
        val settingsJsonString =  settingsInputStream.reader().readText()
        val settings  = gson.fromJson<Settings>(settingsJsonString, Settings::class.java)

        val indexInputStream = indexFile.inputStream()
        val indexJsonString = indexInputStream.reader().readText()
        val index = gson.fromJson<Int>(indexJsonString, Int::class.java)

        if (index != null && settings != null && playlists != null)
            return Config(index = index, settings = settings, playLists = playlists)
        else if(index != null&&settings != null){
            return Config(index = index, settings = settings)
        }
        else if(settings != null && playlists != null){
            return Config(settings = settings, playLists = playlists)
        }
        else if(index != null && playlists != null){
            return Config(index = index, playLists = playlists)
        }
        else if(index != null){
            return Config(index = index)
        }
        else if(settings != null){
            return Config(settings = settings)
        }
        else if(playlists != null){
            return Config(playLists = playlists)
        }

        return Config()
    }

    private fun writeConfigToFile() {
        val playListsJsonString = gson.toJson(currentConfig.playLists)
        val settingsJsonString = gson.toJson(currentConfig.settings)
        val indexJsonString = gson.toJson(currentConfig.index)

        if(!homePath.exists()) homePath.mkdir()
        if(!playlistsFile.exists()) playlistsFile.createNewFile()
        if(!settingsFile.exists()) settingsFile.createNewFile()
        if(!indexFile.exists()) settingsFile.createNewFile()

        val playlistsOutputStream = playlistsFile.outputStream()
        try {
            playlistsOutputStream.writer().write(playListsJsonString)
        }
        catch (e: Exception){
            throw e
        }finally {
            playlistsOutputStream.close()
        }

        val settingsOutputStream = settingsFile.outputStream()
        try {
            settingsOutputStream.writer().write(settingsJsonString)
        }
        catch (e: Exception){
            throw e
        }finally {
            settingsOutputStream.close()
        }

        val indexOutputStream = indexFile.outputStream()
        try {
            indexOutputStream.writer().write(indexJsonString)
        }
        catch (e: Exception){
            throw e
        }finally {
            settingsOutputStream.close()
        }
    }

}