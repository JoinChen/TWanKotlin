package com.bksx.twankotlin.model

import com.bksx.twankotlin.bean.ThirdBean
import com.bksx.twankotlin.contract.IThirdModel
import com.bksx.twankotlin.net.BaseObserver
import com.bksx.twankotlin.net.RetrofitUtil
import com.bksx.twankotlin.net.RxSchedulers

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-9:46
 */
class ThirdTreeModel : IThirdModel {
    override fun getThirdTree(index: Int, cid: Int, listener: IThirdModel.OnGetThirdTreeListener) {
        RetrofitUtil.mInstance
            .getService()
            .getTreeArticle(index, cid)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<ThirdBean>{
                override fun onFailed() {
                    listener.onGetThirdTree(null)
                }

                override fun onSuccess(value: ThirdBean) {
                    listener.onGetThirdTree(value)
                }
            })
    }
}