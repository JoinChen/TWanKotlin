package com.bksx.twankotlin.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.view.View
import android.widget.Toast
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.BaseMVPActivity
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.bean.LoginBean
import com.bksx.twankotlin.contract.ILoginView
import com.bksx.twankotlin.presenter.LoginPresenter
import com.bksx.twankotlin.utils.loadImage
import com.bksx.twankotlin.utils.putSP
import com.bksx.twankotlin.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseMVPActivity<ILoginView, LoginPresenter>(),
    ILoginView {
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var repassword: String
    private var maxMargin: Float = 0.0f
    private var reLayoutObject: ObjectAnimator? = null
    private var btnLoginObject: ObjectAnimator? = null
    private var reLayoutAlphaObject: ObjectAnimator? = null
    private var btnLoginAlphaObject: ObjectAnimator? = null


    private val mLoginPresenter: LoginPresenter by lazy { getPresenter() }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initData() {
        maxMargin = 150f
        toast(maxMargin.toString())
    }

    override fun initView() {
        this.loadImage(R.mipmap.timg, iv_login)
        btn_login.apply {
            text = "LOGIN"
            textSize = 15F
            setTextColor(this.resources.getColor(R.color.white))
        }

        btn_register.apply {
            text = "REGISTER"
            textSize = 15F
            setTextColor(this.resources.getColor(R.color.white))
        }
    }

    override fun initEvent() {
        btn_login.setOnClickListener {
            username = et_username.text.toString().trim()
            password = et_password.text.toString().trim()
            mLoginPresenter.login(this@LoginActivity, username, password)
        }

        btn_register.setOnClickListener {
            when (btn_login.visibility) {
                View.GONE -> {
                    register()
                }
                View.VISIBLE -> {
//                    layout_confirm_pwd.visibility = View.VISIBLE
                    setAnimator()
                }
                else -> register()
            }
        }
    }

    private fun register() {
        username = et_username.text.toString().trim()
        password = et_password.text.toString().trim()
        repassword = et_confirm_pwd.text.toString().trim()
        mLoginPresenter.register(this, username, password, repassword)
    }

    override fun getPresenter(): LoginPresenter = LoginPresenter(this@LoginActivity)

    //ILoginView接口方法
    override fun showLoginResult(bean: LoginBean) {
        putSP(Constant.SP_USERNAME, bean.data.username)
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun showRegisterResult(bean: LoginBean) {
        // bt_login enter
        btn_login.visibility = View.VISIBLE
        btnLoginObject = ObjectAnimator.ofFloat(btn_login, "x", btn_login.width + maxMargin, btn_register.left.toFloat())
        btnLoginAlphaObject = ObjectAnimator.ofFloat(btn_login, "alpha", 0.1f, 1f)
        // et_repassword missing
        reLayoutObject = ObjectAnimator.ofFloat(layout_confirm_pwd, "x", maxMargin, -(et_confirm_pwd.width).toFloat())
        reLayoutAlphaObject = ObjectAnimator.ofFloat(layout_confirm_pwd, "alpha", 1f, 0f)
        startAnimat(layout_password)
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        super.onBackPressed()
    }

    private fun setAnimator() {
        btnLoginObject = ObjectAnimator.ofFloat(
            btn_login, "x",
            maxMargin, -500f
        )
        btnLoginAlphaObject = ObjectAnimator.ofFloat(btn_login, "alpha", 1f, 0f)

        layout_confirm_pwd.visibility = View.VISIBLE
        reLayoutObject = ObjectAnimator.ofFloat(
            layout_confirm_pwd, "x",
            layout_confirm_pwd.width + maxMargin, maxMargin
        )
        reLayoutAlphaObject = ObjectAnimator.ofFloat(layout_confirm_pwd, "alpha", 0f, 1f)

        startAnimat(btn_login)
    }

    private fun startAnimat(view: View) {
        val animatorSet = AnimatorSet()
        animatorSet.apply {
            playTogether(btnLoginObject, btnLoginAlphaObject,reLayoutObject,reLayoutAlphaObject)
            duration = 1000
            start()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    view.visibility = View.GONE
                }
            })
        }
    }
}
