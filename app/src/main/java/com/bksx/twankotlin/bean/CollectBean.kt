package com.bksx.twankotlin.bean

import com.bksx.twankotlin.base.BaseBean

/**
 * @Author JoneChen
 * @Date 2019\7\31 0031-15:37
 */
data class CollectBean(
    val `data`: Data = Data()
):BaseBean(){
    data class Data(
        val curPage: Int = 0,
        val datas: MutableList<DataX> = mutableListOf(),
        val offset: Int = 0,
        val over: Boolean = false,
        val pageCount: Int = 0,
        val size: Int = 0,
        val total: Int = 0
    ){
        data class DataX(
            val author: String = "",
            val chapterId: Int = 0,
            val chapterName: String = "",
            val courseId: Int = 0,
            val desc: String = "",
            val envelopePic: String = "",
            val id: Int = 0,
            val link: String = "",
            val niceDate: String = "",
            val origin: String = "",
            val originId: Int = 0,
            val publishTime: Long = 0,
            val title: String = "",
            val userId: Int = 0,
            val visible: Int = 0,
            val zan: Int = 0
        )
    }
}



