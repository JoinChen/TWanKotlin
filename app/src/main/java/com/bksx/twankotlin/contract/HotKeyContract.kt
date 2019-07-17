package com.bksx.twankotlin.contract

import com.bksx.twankotlin.bean.HotKeyBean
import com.bksx.twankotlin.bean.db.HotKeyDB

/**
 * @Author JoneChen
 * @Date 2019\7\11 0011-13:54
 */

interface IHotKeyModel {

    /*获取热键标签*/
    fun getHotKey(listener: OnGetHotKeyListener)

    /*点击标签或者搜索关键词后将其保存到数据库中-litepal*/
    fun saveKey2DB(key: String)

    /*查找所有的关键词-从数据库中*/
    fun findAllKey(listener: OnGetHotKeyListener)

    interface OnGetHotKeyListener {
        fun onListener(hotKeyBean: HotKeyBean?)

        fun onAllKeyListener(hotKeyMultiList: MutableList<HotKeyDB>)
    }
}

interface IHotKeyView {
    fun showHotKey(hotKeyBean: HotKeyBean)

    fun showHotKeyDB(hotKeyMultiList: MutableList<HotKeyDB>)
}