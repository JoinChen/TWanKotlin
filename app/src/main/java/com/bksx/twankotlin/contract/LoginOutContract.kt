package com.bksx.twankotlin.contract

import com.bksx.twankotlin.base.IBaseView
import com.bksx.twankotlin.bean.LoginBean

/**
 * @Author JoneChen
 * @Date 2019\7\23 0023-9:25
 */

interface ILoginOutView : IBaseView {
    fun showLogOutView(bean: LoginBean)
}

interface ILoginOutModel {

    fun getLogOut(listener: OnGetLogOutListener)

    interface OnGetLogOutListener {
        fun logOutListener(bean: LoginBean?)
    }
}