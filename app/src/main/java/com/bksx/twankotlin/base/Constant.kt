package com.bksx.twankotlin.base

object Constant {
    const val BASE_URL = "https://www.wanandroid.com/"
    const val SET_COOKIE = "set-cookie"
    const val COOKIE="Cookie"

    //sp tag
    const val SP_COOKIE = "cookie"
    const val SP_USERNAME = "userName"

    // request code
    const val SETTING2LOGIN = 0x01

    //登录注册
    const val wan_login = "user/login"
    const val wan_register = "user/register"
    //退出登录
    const val wan_logout = "user/logout/json"

    /**
     * 搜索
     */
    const val wan_hot_key = "hotkey/json" // hot_key
    const val wan_query = "article/query/" // index/post: 'index' in url, 'k' in body

    /**
     * 首页banner
     */
    const val wan_banner = "banner/json"

    /**
     * 首页文章
     */
    const val wan_artical = "article/list/"

    /*
    * 知识体系tree
    * */
    const val wan_tree = "tree/json"

    /**
     * 微信公众号
     */
    const val wan_wx = "wxarticle/chapters/json"

    /**
     * 微信公众号历史文章
     */
    const val wan_wx_history = "wxarticle/list/"

    /**
     * 登录用户收藏
     */
    const val wan_collect_user = "lg/collect/"
    const val wan_collect_cancle = "lg/uncollect_originId/"
    const val wan_collect_all = "lg/collect/list/"

    /**
     * X5Webview界面显示
     */
    const val SEARCH_KEY = "searchKey"
    const val SEARCH_LINK_KEY = "searchLinkKey"
    const val X5_IS_COLLECTED = "isCollected"
    const val X5_ARTICLE_ID = "articleId"
    const val X5_IS_SHOW = "isShow"
    const val X5_COLLECTED_TIME = "collectTime"
    const val X5_PUBLISH_TIME = "publishTime"
    const val X5_ARTICLE_TITLE = "title"
    const val X5_AUTHOR = "author"
    const val CHAPTER_ID = "chapterId"
    const val CHAPTER_NAME = "chapterName"
    const val Third_KEY = "thirdKey"
    const val SECOND_CLICK_POSITION = "secondClickP"
    const val THIRD_TITLE = "thirdTitle"

}