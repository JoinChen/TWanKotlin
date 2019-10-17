package com.bksx.twankotlin.contract

import com.bksx.twankotlin.base.IBaseView
import com.bksx.twankotlin.bean.LoginBean

/**
 * @Author JoneChen
 * @Date 2019\7\19 0019-14:25
 */
interface ILoginView : IBaseView {
    fun showLoginResult(bean: LoginBean)

    fun showRegisterResult(bean: LoginBean)
}

interface ILoginModel {
    fun getLogin(username: String, password: String, listener: OnGetLoginListener)

    interface OnGetLoginListener {
        fun onGetLogin(bean: LoginBean?)
    }

    fun getRegister(username: String, password: String, rePassword: String, listener: OnGetRegisterListener)

    interface OnGetRegisterListener {
        fun onGetRegister(bean: LoginBean?)
    }
}