package com.bksx.twankotlin.contract

import com.bksx.twankotlin.base.IBaseView
import com.bksx.twankotlin.bean.ThirdBean

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-9:38
 */
interface IThirdTreeView : IBaseView {
    fun showThirdTree(secondList: MutableList<ThirdBean.Data.DataX>)

    fun finishedRefresh()
}

interface IThirdModel {
    fun getThirdTree(index: Int, cid: Int, listener: OnGetThirdTreeListener)

    interface OnGetThirdTreeListener {
        fun onGetThirdTree(secondBean: ThirdBean?)
    }
}