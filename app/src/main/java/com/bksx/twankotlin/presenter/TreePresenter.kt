package com.bksx.twankotlin.presenter

import com.bksx.twankotlin.base.BasePresenter
import com.bksx.twankotlin.bean.TreeBean
import com.bksx.twankotlin.contract.ITreeModel
import com.bksx.twankotlin.contract.ITreeView
import com.bksx.twankotlin.model.TreeModel

/**
 * @Author JoneChen
 * @Date 2019\7\25 0025-15:54
 */
class TreePresenter(private val mView:ITreeView):BasePresenter<TreeBean>(),
ITreeModel.OnGetTreeListener{

    val model = TreeModel()
    fun getTree() = model.getTree(this)

    override fun onGetTree(treeBean: TreeBean?) {
        treeBean?.data?.let {
            mView.showTree(it)
        }
    }
}