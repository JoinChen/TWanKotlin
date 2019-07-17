package com.bksx.twankotlin

import android.app.Application
import org.litepal.LitePal
import org.litepal.crud.LitePalSupport

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
    }
}