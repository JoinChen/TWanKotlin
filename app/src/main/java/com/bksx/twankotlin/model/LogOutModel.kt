package com.bksx.twankotlin.model

import com.bksx.twankotlin.bean.LoginBean
import com.bksx.twankotlin.contract.ILoginOutModel
import com.bksx.twankotlin.net.BaseObserver
import com.bksx.twankotlin.net.RetrofitUtil
import com.bksx.twankotlin.net.RxSchedulers

/**
 * @Author JoneChen
 * @Date 2019\7\23 0023-10:02
 */
class LogOutModel: ILoginOutModel{

    override fun getLogOut(listener: ILoginOutModel.OnGetLogOutListener) {
        RetrofitUtil.mInstance
            .getService()
            .getLogOut()
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object :BaseObserver<LoginBean>{
                override fun onSuccess(value: LoginBean) {
                    listener.logOutListener(value)
                }

                override fun onFailed() {
                    listener.logOutListener(null)
                }
            })
    }


}