package com.bksx.twankotlin.model

import com.bksx.twankotlin.bean.WxHistoryBean
import com.bksx.twankotlin.contract.IWxHistoryModel
import com.bksx.twankotlin.net.BaseObserver
import com.bksx.twankotlin.net.RetrofitUtil
import com.bksx.twankotlin.net.RxSchedulers

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-17:43
 */
class WxHistoryModel: IWxHistoryModel {
    override fun getWxHistory(cid: Int, index: Int, listener: IWxHistoryModel.OnGetWxHistoryListener) {
        RetrofitUtil.mInstance
            .getService()
            .getWxHistory(cid,index)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<WxHistoryBean>{
                override fun onSuccess(value: WxHistoryBean) {
                    listener.onGetWxHistory(value)
                }

                override fun onFailed() {
                    listener.onGetWxHistory(null)
                }
            })
    }
}