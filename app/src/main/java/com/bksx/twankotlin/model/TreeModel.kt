package com.bksx.twankotlin.model

import com.bksx.twankotlin.bean.TreeBean
import com.bksx.twankotlin.contract.ITreeModel
import com.bksx.twankotlin.net.BaseObserver
import com.bksx.twankotlin.net.RetrofitUtil
import com.bksx.twankotlin.net.RxSchedulers

/**
 * @Author JoneChen
 * @Date 2019\7\25 0025-15:46
 */
class TreeModel:ITreeModel {

    override fun getTree(listener: ITreeModel.OnGetTreeListener) {
        RetrofitUtil.mInstance
            .getService()
            .getTree()
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<TreeBean>{
                override fun onSuccess(value: TreeBean) {
                    listener.onGetTree(value)
                }

                override fun onFailed() {
                    listener.onGetTree(null)
                }
            })
    }
}