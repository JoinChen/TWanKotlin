package com.bksx.twankotlin.bean

import com.bksx.twankotlin.base.BaseBean

data class HotKeyBean(
     val `data`: MutableList<Data>?
):BaseBean(){
    data class Data(
        val id: Int = 0,
        val link: String = "",
        val name: String = "",
        val order: Int = 0,
        val visible: Int = 0
    )
}
