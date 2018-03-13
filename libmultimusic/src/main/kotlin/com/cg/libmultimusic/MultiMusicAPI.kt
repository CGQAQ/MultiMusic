package com.cg.libmultimusic

import kotlin.collections.*
import com.github.kittinunf.fuel.*
import com.github.kittinunf.fuel.core.Request
import com.google.gson.Gson
import org.intellij.lang.annotations.Language



object MultiMusicAPI{
    // Service provider URL
    @Language("Text")
    private val baseUrlString = "https://music.2333.me/"
    private var page: Int = 0
    private var name: String = ""
    private var type: ServiceType = ServiceType.NETEASE

    /**
     *  headers = {'referer': 'https://music.2333.me/',
     * 'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3314.0 Safari/537.36',
     * 'x-requested-with': 'XMLHttpRequest'}
     */

    // Available Music Services
    private val services = listOf<String>(
                "netease", "qq", "kugou", "kuwo", "xiami", "baidu", "1ting", "migu", "lizhi", "qingting", "ximalaya",
                "kg", "5singyc", "5singfc", "soundcloud"
        )


    /**
     * @author Jason(@CG)
     * @sample search(name= "hello", type=ServiceType.NETEASE)
     * @data = {"input": name, "filter": "name", "type": service, "page": page}
     * @param name 歌曲名称 （包括歌手名搜索更准确)
     * @param type 搜索平台
     * @return 搜索结果
     */
    fun search(name: String, type: ServiceType): Result{
        this.page = 1
        this.name = name
        this.type = type
        val result = query(this.name, this.type, this.page)
        return Gson().fromJson(result, Result::class.java)
    }

    fun nextPage(): Result{
        this.page ++
        val result = query(this.name, this.type, this.page)
        return Gson().fromJson(result, Result::class.java)
    }
    fun previousPage(): Result{
        if (-- this.page < 1) this.page = 1
        val result = query(this.name, this.type, this.page)
        return Gson().fromJson(result, Result::class.java)
    }

    private fun query(name: String, type: ServiceType, page: Int): String {
        val request: Request = Fuel.post(this.baseUrlString, listOf("input" to name, "filter" to "name", "type" to services[type.ordinal], "page" to page))
        request.header("Referer" to "https://music.2333.me/", "x-requested-with" to "XMLHttpRequest", "User-Agent" to "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36")
        val (_, _, result) = request.responseString()
        return result.component1() ?: ""
    }


    enum class ServiceType{
        NETEASE,
        QQ,
        KUGOU,
        KUWO,
        XIAMI,
        BAIDU,
        `1TING`,
        MIGU,
        LIZHI,
        QINGTING,
        XIMALAYA,
        KEIGE,
        `5SINGYINYUE`,
        `5SINGFANCHANG`,
        SOUNDCLOUD
    }
}
