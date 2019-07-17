package com.bksx.twankotlin.model

import com.bksx.twankotlin.bean.QueryBean
import com.bksx.twankotlin.contract.ISearchRltModel
import com.bksx.twankotlin.net.BaseObserver
import com.bksx.twankotlin.net.RetrofitUtil
import com.bksx.twankotlin.net.RxSchedulers

/**
 * @Author JoneChen
 * @Date 2019\7\16 0016-15:25
 */
class SearchResultModel: ISearchRltModel {

    override fun getSearch(index: Int, key: String, listener: ISearchRltModel.OnGetSearchListener) {
        RetrofitUtil.mInstance
            .getService()
            .query(index,key)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object :BaseObserver<QueryBean>{
                override fun onSuccess(value: QueryBean) {
                    listener.onSearchListener(value)
                }

                override fun onFailed() {
                    listener.onSearchListener(null)
                }
            })
    }
}