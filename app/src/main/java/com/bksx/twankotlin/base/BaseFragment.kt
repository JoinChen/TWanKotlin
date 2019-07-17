package com.bksx.twankotlin.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bksx.twankotlin.R
import kotlinx.android.synthetic.main.base_loading_view.view.*

abstract class BaseFragment : Fragment(),IBaseView {
    private var baseLoadingView :BaseLoadingView ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (getLayouId()!=0){
            inflater.inflate(getLayouId(),container,false)
        }else{
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLoadingView()
        initData()
        initView()
        initEvent()
    }

    abstract fun initView()

    abstract fun initData()

    abstract fun initEvent()

    abstract fun getLayouId():Int

    private fun initLoadingView(){
        baseLoadingView = BaseLoadingView(this.context!!, R.style.transparent_dialog)
    }

    private fun showLoadingView(){
        if (null != baseLoadingView){
            baseLoadingView!!.show()
        }else{
            initLoadingView()
            baseLoadingView!!.show()
        }
    }

    private fun hideLoadingView(){
        if (null!=baseLoadingView && baseLoadingView!!.isShowing){
            baseLoadingView!!.cancel()
        }
    }

    override fun showDialog() {
        showLoadingView()
    }

    override fun hideDialog() {
        hideLoadingView()
    }
}