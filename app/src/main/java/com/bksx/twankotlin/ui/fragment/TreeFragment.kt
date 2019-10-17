package com.taonce.wankotlin.ui.fragment

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.BaseFragment
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.bean.TreeBean
import com.bksx.twankotlin.contract.ITreeView
import com.bksx.twankotlin.presenter.TreePresenter
import com.bksx.twankotlin.ui.SecondTreeActivity
import com.bksx.twankotlin.ui.ThirdTreeActivity
import com.bksx.twankotlin.ui.adapter.TreeAdapter
import kotlinx.android.synthetic.main.fragment_tree.*

/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

class TreeFragment : BaseFragment(), ITreeView {
    private var mPresenter: TreePresenter? = null
    private var mMultableList: MutableList<TreeBean.Data> = mutableListOf()
    private var mAdapter: TreeAdapter? = null

    override fun getLayouId(): Int {
        return R.layout.fragment_tree
    }

    override fun initData() {
        mPresenter = TreePresenter(this)
        mAdapter = TreeAdapter(this.context!!,R.layout.item_fragment_tree,mMultableList)
        rl_tree.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(this.context!!,3)
        }
        mAdapter?.notifyDataSetChanged()
        mAdapter?.setOnItemClickListener {position ->
            val value: Int = position
            val intent = Intent(this.context!!, SecondTreeActivity::class.java)
            intent.putExtra(Constant.SECOND_CLICK_POSITION,value)
            context!!.startActivity(intent)
        }
    }

    override fun initView() {

    }

    override fun initEvent() {
        mPresenter?.getTree()
    }

    override fun showTree(treeBean: MutableList<TreeBean.Data>) {
        treeBean.let {
            mMultableList.addAll(it)
        }
    }
}
