package com.bksx.twankotlin.presenter

import android.widget.ActionMenuView
import com.bksx.twankotlin.App
import com.bksx.twankotlin.base.BasePresenter
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.bean.LoginBean
import com.bksx.twankotlin.contract.ILoginOutModel
import com.bksx.twankotlin.contract.ILoginOutView
import com.bksx.twankotlin.model.LogOutModel
import com.bksx.twankotlin.utils.getSP
import com.bksx.twankotlin.utils.putSP
import com.bksx.twankotlin.utils.toast

/**
 * @Author JoneChen
 * @Date 2019\7\23 0023-9:27
 */
class LogOutPresenter(private val mView: ILoginOutView) :
    BasePresenter<ILoginOutView>(),
    ILoginOutModel.OnGetLogOutListener {

    private val model = LogOutModel()
    private lateinit var userName: String

    fun getLogOut() {
        userName = App.mInstance.getSP(Constant.SP_USERNAME,"") as String
        if (userName.isEmpty()){
            App.mInstance.toast("请登录")
        }else{
            mView.showDialog()
            model.getLogOut(this)
        }
    }

    override fun logOutListener(bean: LoginBean?) {
        mView.hideDialog()
        bean?.let {
            App.mInstance.putSP(Constant.SP_USERNAME,"")
            App.mInstance.toast("LogOut Success!")
            mView.showLogOutView(it)
        }
    }
}