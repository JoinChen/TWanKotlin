package com.bksx.twankotlin.presenter

import com.bksx.twankotlin.base.BasePresenter
import com.bksx.twankotlin.bean.TreeBean
import com.bksx.twankotlin.contract.ISecondTreeModel
import com.bksx.twankotlin.contract.ISecondTreeView
import com.bksx.twankotlin.model.SecondTreeModel

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-13:32
 */
class SecondTreePresenter(private val mView:ISecondTreeView): BasePresenter<ISecondTreeView>() ,
ISecondTreeModel.OnGetSecondTreeListener{
    private val model = SecondTreeModel()

    fun getSecondTree() = kotlin.run{
        mView.showDialog()
        model.getSecondTree(this)
    }

        override fun onGetSecondTree(secondBean: TreeBean?) {
            mView.hideDialog()
            secondBean?.data?.let {
                mView.showSecondTree(it)
            }
        }
    }
