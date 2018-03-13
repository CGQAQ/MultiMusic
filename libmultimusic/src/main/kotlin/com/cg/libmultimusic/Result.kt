package com.cg.libmultimusic

class Result{
    var data: List<DataEntray>? = null
    var code: Int = 0
    var error: String? = null


    inner class DataEntray {
        var type: String? = null
        var link: String? = null
        var songid: String? = null
        var title: String? = null
        var author: String? = null
        var lrc: String? = null
        var url: String? = null
        var pic: String? = null
    }
}