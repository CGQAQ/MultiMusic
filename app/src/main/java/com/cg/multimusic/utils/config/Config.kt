package com.cg.multimusic.utils.config

import com.cg.libmultimusic.Song
import com.cg.multimusic.entry.Settings

/**
 * Created by jason on 3/15/18.
 */
data class Config(var index: Int = 0, val playLists: Map<String, ArrayList<Song>> = HashMap<String, ArrayList<Song>>(), val settings: Settings = Settings())