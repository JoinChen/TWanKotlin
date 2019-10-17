package com.bksx.twankotlin.contract

import com.bksx.twankotlin.base.BaseBean
import com.bksx.twankotlin.base.IBaseView
import com.bksx.twankotlin.bean.CollectBean
import com.bksx.twankotlin.bean.db.CollectOffDB

/**
 * @Author JoneChen
 * @Date 2019\7\17 0017-17:15
 */
interface ICollectView : IBaseView {
    fun showCollect()

    fun showUnColelct()

    fun showCollectOffLine()

    fun showCollectAll()

    fun finishRefresh()

    fun showCollectUserAll(collectList:MutableList<CollectBean.Data.DataX>)
}

interface ICollectModel {
    //获取用户登录的收藏
    fun findUserCollect(index: Int, listener: OnGetUserAllCollectListener)

    //获取离线收藏
    fun findAllCollect(listener: OnGetAllCollectListener)

    fun collectButton(id: Int, listener: OnGetCollectLisetener)

    fun unCollectButton(id: Int, listener: OnGetCollectLisetener)

    fun collectOffLineButton(
        articleId: Int,
        author: String,
        publishTime: String,
        collectTime: String,
        url: String,
        title: String,
        listener: OnGetCollectOffLineListener
    )

    interface OnGetCollectLisetener {
        fun collectListener(baseBean: BaseBean?)

        fun unCollectListener(baseBean: BaseBean?)
    }

    interface OnGetCollectOffLineListener {
        fun onCollectOffSuccess()

        fun onCollectOffFaild()
    }

    interface OnGetAllCollectListener {
        fun onGetAllCollec(mCollectList: MutableList<CollectOffDB>)
    }

    interface OnGetUserAllCollectListener {
        fun onGetUserAllCollect(collectBean: CollectBean?)
    }
}