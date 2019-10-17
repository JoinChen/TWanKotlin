package com.bksx.twankotlin.contract

import com.bksx.twankotlin.base.IBaseView
import com.bksx.twankotlin.bean.TreeBean

/**
 * @Author JoneChen
 * @Date 2019\7\25 0025-15:40
 */

interface ITreeView : IBaseView {
    fun showTree(treeBean: MutableList<TreeBean.Data>)
}

interface ITreeModel {
    fun getTree(listener: OnGetTreeListener)

    interface OnGetTreeListener {
        fun onGetTree(treeBean: TreeBean?)
    }
}