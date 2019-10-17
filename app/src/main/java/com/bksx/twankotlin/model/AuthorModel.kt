package com.bksx.twankotlin.model

import com.bksx.twankotlin.base.BaseBean
import com.bksx.twankotlin.bean.AuthorBean
import com.bksx.twankotlin.contract.IAuthorModel
import com.bksx.twankotlin.net.BaseObserver
import com.bksx.twankotlin.net.RetrofitUtil
import com.bksx.twankotlin.net.RxSchedulers

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-16:40
 */
class AuthorModel: IAuthorModel {

    override fun getAuthor(listener: IAuthorModel.OnGetAuthorListener) {
        RetrofitUtil.mInstance
            .getService()
            .getAuthor()
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<AuthorBean>{
                override fun onSuccess(value: AuthorBean) {
                    listener.onGetAuthor(value)
                }

                override fun onFailed() {
                    listener.onGetAuthor(null)
                }
            })
    }
}