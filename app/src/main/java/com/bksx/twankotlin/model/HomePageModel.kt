package com.bksx.twankotlin.model

import android.database.Observable
import com.bksx.twankotlin.base.ArticleBean
import com.bksx.twankotlin.bean.BannerBean
import com.bksx.twankotlin.contract.IHomeModel
import com.bksx.twankotlin.net.BaseObserver
import com.bksx.twankotlin.net.RetrofitUtil
import com.bksx.twankotlin.net.RxSchedulers

/**
 * @Author JoneChen
 * @Date 2019\7\23 0023-16:22
 */
class HomePageModel: IHomeModel {

    override fun getBanner(listener: IHomeModel.OnGetBannerListener) {
        RetrofitUtil.mInstance
            .getService()
            .getBanner()
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object :BaseObserver<BannerBean>{
                override fun onSuccess(value: BannerBean) {
                    listener.onGetBanner(value)
                }

                override fun onFailed() {
                    listener.onGetBanner(null)
                }
            })
    }

    override fun getArticle(index: Int,listener: IHomeModel.OnGetArticleListener) {
        RetrofitUtil.mInstance
            .getService()
            .getArticle(index)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<ArticleBean>{
                override fun onSuccess(value: ArticleBean) {
                    listener.onGetArticle(value)
                }

                override fun onFailed() {
                    listener.onGetArticle(null)
                }
            })
    }

}