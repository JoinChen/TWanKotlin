package com.bksx.twankotlin.bean

import com.bksx.twankotlin.base.BaseBean

/**
 * @Author JoneChen
 * @Date 2019\7\23 0023-16:13
 */
data class BannerBean(
    val `data`: List<Data>
): BaseBean() {
    data class Data(
        val desc: String = "",
        val id: Int = 0,
        val imagePath: String = "",
        val isVisible: Int = 0,
        val order: Int = 0,
        val title: String = "",
        val type: Int = 0,
        val url: String = ""
    )
}

