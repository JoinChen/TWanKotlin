package com.bksx.twankotlin.model

import com.bksx.twankotlin.bean.TreeBean
import com.bksx.twankotlin.contract.ISecondTreeModel
import com.bksx.twankotlin.net.BaseObserver
import com.bksx.twankotlin.net.RetrofitUtil
import com.bksx.twankotlin.net.RxSchedulers

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-13:29
 */
class SecondTreeModel: ISecondTreeModel {

    override fun getSecondTree(listener: ISecondTreeModel.OnGetSecondTreeListener) {
        RetrofitUtil.mInstance
            .getService()
            .getTree()
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<TreeBean>{
                override fun onSuccess(value: TreeBean) {
                    listener.onGetSecondTree(value)
                }

                override fun onFailed() {
                    listener.onGetSecondTree(null)
                }
            })
    }
}