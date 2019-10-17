package com.bksx.twankotlin.model

import com.bksx.twankotlin.App
import com.bksx.twankotlin.bean.LoginBean
import com.bksx.twankotlin.contract.ILoginModel
import com.bksx.twankotlin.net.BaseObserver
import com.bksx.twankotlin.net.RetrofitUtil
import com.bksx.twankotlin.net.RxSchedulers
import com.bksx.twankotlin.utils.toast

/**
 * @Author JoneChen
 * @Date 2019\7\19 0019-14:24
 */
class LoginModel : ILoginModel{
    override fun getRegister(
        username: String,
        password: String,
        rePassword: String,
        listener: ILoginModel.OnGetRegisterListener
    ) {
        RetrofitUtil.mInstance
            .getService()
            .getRegister(username, password, rePassword)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<LoginBean> {
                override fun onSuccess(value: LoginBean) {
                    listener.onGetRegister(value)
                }

                override fun onFailed() {
                    listener.onGetRegister(null)
                }
            })
    }

    override fun getLogin(
        username: String,
        password: String,
        listener: ILoginModel.OnGetLoginListener
    ) {
        RetrofitUtil.mInstance
            .getService()
            .getLogin(username, password)
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<LoginBean> {
                override fun onSuccess(value: LoginBean) {
                    listener.onGetLogin(value)
                }

                override fun onFailed() {
                    listener.onGetLogin(null)
                }
            })
    }

}