package com.bksx.twankotlin.bean

import com.bksx.twankotlin.base.BaseBean

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-16:37
 */

data class AuthorBean(
    val `data`: MutableList<Data> = mutableListOf()
):BaseBean(){
    data class Data(
        val children: MutableList<Any> = mutableListOf(),
        val courseId: Int = 0,
        val id: Int = 0,
        val name: String = "",
        val order: Int = 0,
        val parentChapterId: Int = 0,
        val userControlSetTop: Boolean = false,
        val visible: Int = 0
    )
}

