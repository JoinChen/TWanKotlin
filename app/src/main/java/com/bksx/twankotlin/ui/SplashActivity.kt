package com.bksx.twankotlin.ui

import android.content.Intent
import android.view.KeyEvent
import android.view.WindowManager
import com.bksx.twankotlin.MainActivity
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.BaseActivity
import com.blankj.utilcode.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_splash.tv_splash_jump
import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.isActive

/**
 * @Author JoneChen
 * @Date 2019\7\3 0003-14:04
 */
class SplashActivity : BaseActivity() {
    private var job :Job?=null

    override fun getLayoutId(): Int = R.layout.activity_splash


    override fun initData() {
    }

    override fun initView() {
    }

    override fun initEvent() {
       job = GlobalScope.launch{
            delay(3000L)
            while (isActive){
                jump2Main()
            }
        }
        ToastUtils.showShortToast(this,"执行主线程")
        tv_splash_jump.setOnClickListener {
            jump2Main()
            job?.cancel()
        }
    }

     private fun jump2Main() {
         val intent = Intent()
         intent.setClass(this, MainActivity::class.java)
         startActivity(intent)
         this.finish()
         job?.cancel()
     }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            this.finish()
            ToastUtils.showShortToast(this,"返回按钮监听")
            job?.cancel()
            return true
        }else if (keyCode == KeyEvent.KEYCODE_HOME){
            finish()
            job?.cancel()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

//    override fun onAttachedToWindow() {
//        this.window.setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG)
//        super.onAttachedToWindow()
//    }
}