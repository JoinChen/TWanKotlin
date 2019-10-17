package com.bksx.twankotlin.contract

import com.bksx.twankotlin.base.IBaseView
import com.bksx.twankotlin.bean.TreeBean

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-10:56
 */
interface ISecondTreeView: IBaseView{
    fun showSecondTree(mList:MutableList<TreeBean.Data>)
}

interface ISecondTreeModel{
    fun getSecondTree(listener: OnGetSecondTreeListener)

    interface OnGetSecondTreeListener{
        fun onGetSecondTree(secondBean: TreeBean?)
    }
}