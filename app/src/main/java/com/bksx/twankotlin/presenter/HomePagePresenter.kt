package com.bksx.twankotlin.presenter

import com.bksx.twankotlin.base.ArticleBean
import com.bksx.twankotlin.base.BasePresenter
import com.bksx.twankotlin.bean.BannerBean
import com.bksx.twankotlin.contract.IHomeModel
import com.bksx.twankotlin.contract.IHomeView
import com.bksx.twankotlin.model.HomePageModel

/**
 * @Author JoneChen
 * @Date 2019\7\23 0023-16:32
 */
class HomePagePresenter(private val mView: IHomeView) : BasePresenter<IHomeView>(),
    IHomeModel.OnGetBannerListener,
    IHomeModel.OnGetArticleListener {
    private val model = HomePageModel()

    fun getBanner() = run {
        mView.showDialog()
        model.getBanner(this)
    }

    fun getArticle(index: Int) = run {
        mView.showDialog()
        model.getArticle(index,this)
    }

        override fun onGetBanner(bannerBean: BannerBean?) {
            bannerBean?.let {
                mView.showHomeBanner(it)
                mView.hideDialog()
            }
        }

        override fun onGetArticle(articleBean: ArticleBean?) {
            mView.finishedRefresh()
            articleBean?.data?.datas?.let {
                mView.showHomeArticle(it)
                mView.hideDialog()
            }
        }
    }
