package com.bksx.twankotlin.contract

import com.bksx.twankotlin.base.ArticleBean
import com.bksx.twankotlin.base.IBaseView
import com.bksx.twankotlin.bean.BannerBean

/**
 * @Author JoneChen
 * @Date 2019\7\23 0023-16:11
 */
interface IHomeView : IBaseView {
    fun showHomeBanner(bannerBean: BannerBean)

    fun showHomeArticle(articleBean: MutableList<ArticleBean.Data.DataX>)

    fun finishedRefresh()
}

interface IHomeModel {

    fun getBanner(listener: OnGetBannerListener)

    interface OnGetBannerListener {
        fun onGetBanner(bannerBean: BannerBean?)
    }

    fun getArticle(index: Int, listener: OnGetArticleListener)

    interface OnGetArticleListener {
        fun onGetArticle(articleBean: ArticleBean?)
    }
}