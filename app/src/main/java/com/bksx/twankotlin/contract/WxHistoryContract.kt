package com.bksx.twankotlin.contract

import com.bksx.twankotlin.base.IBaseView
import com.bksx.twankotlin.bean.WxHistoryBean

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-17:39
 */
interface IWxHistoryView : IBaseView {
    fun showWxHistory(wxHistoryList: MutableList<WxHistoryBean.Data.DataX>)

    fun finishedRefresh()
}

interface IWxHistoryModel {

    fun getWxHistory(cid: Int, index: Int, listener: OnGetWxHistoryListener)

    interface OnGetWxHistoryListener {
        fun onGetWxHistory(wxHistoryBean: WxHistoryBean?)
    }
}