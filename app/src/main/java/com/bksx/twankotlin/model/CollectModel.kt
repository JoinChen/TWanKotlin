package com.bksx.twankotlin.model

import com.bksx.twankotlin.base.BaseBean
import com.bksx.twankotlin.bean.CollectBean
import com.bksx.twankotlin.bean.db.CollectOffDB
import com.bksx.twankotlin.contract.ICollectModel
import com.bksx.twankotlin.net.BaseObserver
import com.bksx.twankotlin.net.RetrofitUtil
import com.bksx.twankotlin.net.RxSchedulers
import org.litepal.LitePal
import org.litepal.extension.find

/**
 * @Author JoneChen
 * @Date 2019\7\18 0018-14:06
 */
class CollectModel : ICollectModel {
    override fun collectButton(id: Int, listener: ICollectModel.OnGetCollectLisetener) {
        RetrofitUtil.mInstance
            .getService()
            .getCollectUser(id)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<BaseBean> {
                override fun onSuccess(value: BaseBean) {
                    listener.collectListener(value)
                }

                override fun onFailed() {
                    listener.collectListener(null)
                }
            })
    }

    override fun unCollectButton(id: Int, listener: ICollectModel.OnGetCollectLisetener) {
        RetrofitUtil.mInstance
            .getService()
            .cancleCollect(id)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<BaseBean> {
                override fun onSuccess(value: BaseBean) {
                    listener.unCollectListener(value)
                }

                override fun onFailed() {
                    listener.unCollectListener(null)
                }
            })
    }

    override fun collectOffLineButton(
        articleId: Int,
        author: String,
        publishTime: String,
        collectTime: String,
        url: String,
        title: String,
        listener: ICollectModel.OnGetCollectOffLineListener
    ) {
        val collectOffList: List<CollectOffDB> = LitePal.where("title = ? ", title).find()
        if (collectOffList.isEmpty()) {
            val collectOffDB = CollectOffDB()
            collectOffDB.run {
                this.articleId = articleId
                this.author = author
                this.publishTime = publishTime
                this.collectTime = collectTime
                this.url = url
                this.title = title
                this.save()
            }
            listener.onCollectOffSuccess()
        } else {
            listener.onCollectOffFaild()
        }
    }

    override fun findAllCollect(listener: ICollectModel.OnGetAllCollectListener) {
        LitePal.order("collectTime desc").findAsync(CollectOffDB::class.java).listen {
            listener.onGetAllCollec(it)
        }
    }

    override fun findUserCollect(index: Int, listener: ICollectModel.OnGetUserAllCollectListener) {
        RetrofitUtil.mInstance
            .getService()
            .getCollectAll(index)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<CollectBean>{
                override fun onSuccess(value: CollectBean) {
                    listener.onGetUserAllCollect(value)
                }

                override fun onFailed() {
                    listener.onGetUserAllCollect(null)
                }
            })
    }
}