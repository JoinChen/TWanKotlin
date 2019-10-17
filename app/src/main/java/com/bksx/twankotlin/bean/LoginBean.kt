package com.bksx.twankotlin.bean

import com.bksx.twankotlin.base.BaseBean

/**
 * @Author JoneChen
 * @Date 2019\7\19 0019-14:20
 */
data class LoginBean(
    val `data`: Data
) : BaseBean() {
    data class Data(
        val admin: Boolean = false,
        val chapterTops: List<Any> = listOf(),
        val collectIds: List<Any> = listOf(),
        val email: String = "",
        val icon: String = "",
        val id: Int = 0,
        val nickname: String = "",
        val password: String = "",
        val token: String = "",
        val type: Int = 0,
        val username: String = ""
    )
}

