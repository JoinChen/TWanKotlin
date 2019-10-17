package com.bksx.twankotlin.base

/**
 * @Author JoneChen
 * @Date 2019\7\23 0023-16:19
 */
data class ArticleBean(
    val `data`: Data = Data()
): BaseBean() {
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
            val apkLink: String = "",
            val author: String = "",
            val chapterId: Int = 0,
            val chapterName: String = "",
            val collect: Boolean = false,
            val courseId: Int = 0,
            val desc: String = "",
            val envelopePic: String = "",
            val fresh: Boolean = false,
            val id: Int = 0,
            val link: String = "",
            val niceDate: String = "",
            val origin: String = "",
            val prefix: String = "",
            val projectLink: String = "",
            val publishTime: Long = 0,
            val superChapterId: Int = 0,
            val superChapterName: String = "",
            val tags: List<Any> = listOf(),
            val title: String = "",
            val type: Int = 0,
            val userId: Int = 0,
            val visible: Int = 0,
            val zan: Int = 0
        )
    }

}



