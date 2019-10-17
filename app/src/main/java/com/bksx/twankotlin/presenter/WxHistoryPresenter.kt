package com.bksx.twankotlin.presenter

import com.bksx.twankotlin.App
import com.bksx.twankotlin.base.BasePresenter
import com.bksx.twankotlin.bean.WxHistoryBean
import com.bksx.twankotlin.contract.IWxHistoryModel
import com.bksx.twankotlin.contract.IWxHistoryView
import com.bksx.twankotlin.model.AuthorModel
import com.bksx.twankotlin.model.WxHistoryModel
import com.bksx.twankotlin.utils.toast

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-17:47
 */
class WxHistoryPresenter(private val mView:IWxHistoryView): BasePresenter<IWxHistoryView>(),
IWxHistoryModel.OnGetWxHistoryListener{
    private val model = WxHistoryModel()

    fun getWxHistory(cid:Int,index:Int){
        mView.showDialog()
        model.getWxHistory(cid,index,this)
    }

    override fun onGetWxHistory(wxHistoryBean: WxHistoryBean?) {
        mView.hideDialog()
        mView.finishedRefresh()
        wxHistoryBean?.data?.datas?.let {
            if (it.isNotEmpty()){
                mView.showWxHistory(it)
            }else{
                App.mInstance.toast("no more!")
            }
        }
    }
}