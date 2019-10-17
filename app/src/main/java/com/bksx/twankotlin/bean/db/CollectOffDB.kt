package com.bksx.twankotlin.bean.db

import org.litepal.crud.LitePalSupport

/**
 * @Author JoneChen
 * @Date 2019\7\30 0030-17:23
 */
class CollectOffDB : LitePalSupport() {
    var id: Long = 0
    var articleId: Int = -1
    var url: String = ""
    var tag: Int = 1
    var collectTime: String = ""
    var publishTime: String = ""
    var author: String = ""
    var title: String = ""
    var originId: Int = -1
    override fun toString(): String {
        return "CollectOffDB(id=$id, articleId=$articleId, url='$url', tag=$tag, collectTime='$collectTime', publishTime='$publishTime', author='$author', title='$title', originId=$originId)"
    }
}