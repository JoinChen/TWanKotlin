package com.bksx.twankotlin

import android.app.Application
import com.blankj.utilcode.utils.LogUtils
import com.tencent.smtt.sdk.QbSdk
import org.litepal.LitePal

class App : Application(){

    companion object{
        @JvmStatic
        lateinit var mInstance:App
        private set
    }

    override fun onCreate() {
        mInstance = this
        super.onCreate()
        LitePal.initialize(this)

        //加载x5内核
        initX5()
    }

    private fun initX5() {
        QbSdk.setDownloadWithoutWifi(true)
        val callback: QbSdk.PreInitCallback = object : QbSdk.PreInitCallback {
            override fun onCoreInitFinished() {

            }

            override fun onViewInitFinished(p0: Boolean) {
                //加载x5内核成功后返回true
               LogUtils.e("X5内核是否加载成功","$p0")
            }
        }

        QbSdk.initX5Environment(this@App,callback)
    }
}