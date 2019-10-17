package com.bksx.twankotlin.presenter

import android.content.Context
import android.text.TextUtils
import com.bksx.twankotlin.App
import com.bksx.twankotlin.base.BasePresenter
import com.bksx.twankotlin.bean.LoginBean
import com.bksx.twankotlin.contract.ILoginModel
import com.bksx.twankotlin.contract.ILoginView
import com.bksx.twankotlin.model.LoginModel
import com.bksx.twankotlin.utils.toast

/**
 * @Author JoneChen
 * @Date 2019\7\19 0019-14:54
 */
class LoginPresenter(private val mView: ILoginView) : BasePresenter<ILoginView>(),
    ILoginModel.OnGetLoginListener,
    ILoginModel.OnGetRegisterListener {

    private val model = LoginModel()
    fun login(context: Context, userName: String, password: String) {
        when {
            TextUtils.isEmpty(userName) -> {
                context.toast("userName can not be empty! ")
                return
            }
            TextUtils.isEmpty(password) -> {
                context.toast("password can not be empty! ")
                return
            }
            else -> model.getLogin(userName, password, this)
        }
    }

    fun register(context: Context, userName: String, password: String, rePassword: String) {
        when {
            TextUtils.isEmpty(userName) -> {
                context.toast("userName can not be empty! ")
                return
            }
            TextUtils.isEmpty(password) -> {
                context.toast("password can not be empty! ")
                return
            }
            TextUtils.isEmpty(rePassword) -> {
                context.toast("rePassword can not be empty! ")
                return
            }
            else -> model.getRegister(userName, password, rePassword, this)
        }
    }

    override fun onGetLogin(bean: LoginBean?) {
        bean?.let {
            mView.showLoginResult(it)
        }
    }

    override fun onGetRegister(bean: LoginBean?) {
        bean?.let { mView.showRegisterResult(it) }
    }

}