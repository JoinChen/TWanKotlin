package com.bksx.twankotlin.presenter

import com.bksx.twankotlin.App
import com.bksx.twankotlin.base.BaseBean
import com.bksx.twankotlin.base.BasePresenter
import com.bksx.twankotlin.bean.CollectBean
import com.bksx.twankotlin.bean.db.CollectOffDB
import com.bksx.twankotlin.contract.ICollectModel
import com.bksx.twankotlin.contract.ICollectView
import com.bksx.twankotlin.model.CollectModel
import com.bksx.twankotlin.utils.toast

/**
 * @Author JoneChen
 * @Date 2019\7\17 0017-17:32
 */
class CollectionPresenter(private val mView: ICollectView) : BasePresenter<ICollectView>(),
    ICollectModel.OnGetCollectLisetener,
    ICollectModel.OnGetCollectOffLineListener,
    ICollectModel.OnGetAllCollectListener,
    ICollectModel.OnGetUserAllCollectListener {
    private val model = CollectModel()
    private val context by lazy { App.mInstance }

    fun getCollectUser(id: Int) {
        id.let {
            model.collectButton(id, this)
        }
    }

    fun getUnCollect(id: Int) {
        id.let {
            model.unCollectButton(id, this)
        }
    }

    fun getCollectOffLine(
        articleId: Int,
        author: String,
        publishTime: String,
        collectTime: String,
        url: String,
        title: String
    ) {
        mView.showDialog()
        model.collectOffLineButton(
            articleId,
            author,
            publishTime,
            collectTime,
            url,
            title,
            this@CollectionPresenter
        )
    }

    fun getAllCollect() = model.findAllCollect(this)

    fun getUserAllCollect(index: Int) = kotlin.run {
        mView.showDialog()
        model.findUserCollect(index, this)
    }

    override fun collectListener(baseBean: BaseBean?) {
        baseBean?.let {
            context.toast("收藏成功！")
            mView.showCollect()
        }
    }

    override fun unCollectListener(baseBean: BaseBean?) {
        baseBean?.let {
            context.toast("取消收藏成功！")
            mView.showUnColelct()
        }
    }

    override fun onGetAllCollec(mCollectList: MutableList<CollectOffDB>) {
        if (mCollectList.isNotEmpty()) mView.showCollectAll()
    }

    override fun onCollectOffSuccess() {
        mView.hideDialog()
        context.toast("离线收藏成功！")
    }

    override fun onCollectOffFaild() {
        mView.hideDialog()
        context.toast("离线收藏操作失败！")
    }

    override fun onGetUserAllCollect(collectBean: CollectBean?) {
        mView.hideDialog()
        mView.finishRefresh()
        collectBean?.data?.datas?.let {
            if (it.isNotEmpty()) {
                mView.showCollectUserAll(it)
            }else{
                context.toast("no more!")
            }
        }
    }
}