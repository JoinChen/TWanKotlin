package com.bksx.twankotlin.ui

import android.app.Activity
import android.content.Intent
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.BaseMVPActivity
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.bean.LoginBean
import com.bksx.twankotlin.contract.ILoginOutView
import com.bksx.twankotlin.presenter.LogOutPresenter
import com.bksx.twankotlin.ui.dialog.SettingDialog
import com.bksx.twankotlin.utils.getSP
import com.bksx.twankotlin.utils.start
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * @Author JoneChen
 * @Date 2019\7\22 0022-15:09
 */
class SettingActivity : BaseMVPActivity<ILoginOutView, LogOutPresenter>(), ILoginOutView {
    private lateinit var mDialog: SettingDialog
    private lateinit var userName: String
    private val mPresenter: LogOutPresenter = getPresenter()

    override fun getLayoutId(): Int = R.layout.activity_setting

    override fun initData() {
        mDialog = SettingDialog(this, "Are you want to quit!")
        setRefreshName()
    }

    private fun setRefreshName() {
        userName = getSP(Constant.SP_USERNAME, "") as String
        if (userName.isEmpty()) {
            tv_setting_username.text = "点击登录"
        } else {
            tv_setting_username.text = userName
        }
    }

    override fun initView() {
    }

    override fun initEvent() {
        tv_setting_quit.setOnClickListener {
            mDialog.show()
        }
        mDialog.setEnterListener {
            mPresenter.getLogOut()
        }

        tv_setting_username.setOnClickListener {
            if (userName.isEmpty()) {
                start(LoginActivity::class.java, requestCode = Constant.SETTING2LOGIN)
            }
        }

        tv_setting_store.setOnClickListener {
            start(CollectActivity::class.java)
        }
    }

    override fun getPresenter(): LogOutPresenter = LogOutPresenter(this)

    override fun showLogOutView(bean: LoginBean) {
        setRefreshName()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.SETTING2LOGIN) {
            setRefreshName()
        }
    }
}