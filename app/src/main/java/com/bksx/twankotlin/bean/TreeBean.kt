package com.bksx.twankotlin.bean

import com.bksx.twankotlin.base.BaseBean

/**
 * @Author JoneChen
 * @Date 2019\7\25 0025-15:30
 */

data class TreeBean(
    val `data`: MutableList<Data> = mutableListOf()
    ):BaseBean(){

    data class Data(
        val children: MutableList<Children> = mutableListOf(),
        val courseId: Int = 0,
        val id: Int = 0,
        val name: String = "",
        val order: Int = 0,
        val parentChapterId: Int = 0,
        val userControlSetTop: Boolean = false,
        val visible: Int = 0
    ){
        data class Children(
            val children: List<Any> = listOf(),
            val courseId: Int = 0,
            val id: Int = 0,
            val name: String = "",
            val order: Int = 0,
            val parentChapterId: Int = 0,
            val userControlSetTop: Boolean = false,
            val visible: Int = 0
        )
    }

}






