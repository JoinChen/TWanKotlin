package com.bksx.twankotlin.bean.db

import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport

/**
 * @Author JoneChen
 * @Date 2019\7\12 0012-14:09
 */
class HotKeyDB() : LitePalSupport() {
    val id: Long = 0
    @Column(unique = true,defaultValue = "Android")
    var key: String = ""
    var times:Int = 0
}
