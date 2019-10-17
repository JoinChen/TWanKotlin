package com.bksx.twankotlin.presenter

import com.bksx.twankotlin.App
import com.bksx.twankotlin.base.BasePresenter
import com.bksx.twankotlin.bean.ThirdBean
import com.bksx.twankotlin.contract.ISecondTreeView
import com.bksx.twankotlin.contract.IThirdModel
import com.bksx.twankotlin.contract.IThirdTreeView
import com.bksx.twankotlin.model.ThirdTreeModel
import com.bksx.twankotlin.utils.toast

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-9:53
 */
class ThirdTreePresenter(private val mView: IThirdTreeView) : BasePresenter<ISecondTreeView>(),
    IThirdModel.OnGetThirdTreeListener {
    private val model = ThirdTreeModel()

    fun getSecondTree(index: Int, cid: Int){
        mView.showDialog()
        model.getThirdTree(index,cid,this)
    }

    override fun onGetThirdTree(thirdBean: ThirdBean?) {
        mView.hideDialog()
        mView.finishedRefresh()
        thirdBean?.data?.datas?.let {
            if (it.isNotEmpty()){
                mView.showThirdTree(it)
            }else{
                App.mInstance.toast("no more!")
            }
        }
    }
}